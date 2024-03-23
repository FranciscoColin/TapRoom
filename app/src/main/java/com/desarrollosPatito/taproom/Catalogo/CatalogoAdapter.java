package com.desarrollosPatito.taproom.Catalogo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.desarrollosPatito.taproom.Catalogo.Edita.ActivityEdit;
import com.desarrollosPatito.taproom.R;
import com.desarrollosPatito.taproom.Utilidades.ClienteTapRoom;
import com.desarrollosPatito.taproom.Utilidades.Generales;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.FichaViewHolder> {

    private Context context;
    private List<ObjCerveza> fichaItemList;

    Intent intent;
    Dialog dialogoCarga;
    public CatalogoAdapter(Context context, List<ObjCerveza> fichaItemList) {
        this.context = context;
        this.fichaItemList = fichaItemList;
    }

    @NonNull
    @Override
    public FichaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ficha_catalogo, parent, false);
        return new FichaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FichaViewHolder holder, int position) {
        ObjCerveza fichaItem = fichaItemList.get(position);
        holder.nombreTextView.setText((position+1) + " - "+fichaItem.getBeerName());
        holder.id.setText(fichaItem.getId());

        int backgroundColor = fichaItem.getStatus().equals("1") ? ContextCompat.getColor(context, R.color.activo) : ContextCompat.getColor(context, R.color.inactivo);

        holder.ficha.setCardBackgroundColor(backgroundColor);
        // Establecer eventos de clic para los botones
        holder.editaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ActivityEdit.class);
                intent.putExtra("nombre", fichaItem.getBeerName());
                intent.putExtra("estilo", fichaItem.getBeerStyle());
                intent.putExtra("id", fichaItem.getId());
                intent.putExtra("abv", String.valueOf(fichaItem.getAbv()));
                intent.putExtra("casa", fichaItem.getBrewery());
                intent.putExtra("descripcion", fichaItem.getFlavorDescription());
                intent.putExtra("ibu", String.valueOf(fichaItem.getIbu()));
                intent.putExtra("origen", fichaItem.getOrigin());
                intent.putExtra("precio", String.valueOf(fichaItem.getPrice()));
                intent.putExtra("tamanio", fichaItem.getServingSize());
                intent.putExtra("status",fichaItem.getStatus());
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        });


        holder.borraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("¿Estás seguro de que quieres borrar esto?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogoCarga = Generales.mostrarDialogoCargaConImagen(context);

                        String idCerveza = fichaItem.getId(); // Asegúrate de tener un getter para Id en ObjCerveza

                        Call<ResponseDelete> call = ClienteTapRoom.getClient().create(ApiCatalogo.class).actualizarRegistro(idCerveza);
                        call.enqueue(new Callback<ResponseDelete>() {
                            @Override
                            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    String successMessage = response.body().getSuccess();
                                    if (successMessage != null) {
                                        Toast.makeText(context, "Elemento eliminado correctamente", Toast.LENGTH_SHORT).show();
                                        eliminarElemento(position);
                                    } else {
                                        String errorMessage = response.body().getError();
                                        Toast.makeText(context, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(context, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                                }
                                Generales.ocultarDialogoCargaConImagen(dialogoCarga);
                            }

                            @Override
                            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                                Toast.makeText(context, "Error en la conexión", Toast.LENGTH_SHORT).show();
                                Log.e("CatalogoAdapter", "Error al intentar eliminar cerveza", t);
                                Generales.ocultarDialogoCargaConImagen(dialogoCarga);
                            }
                        });

                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancelar", (dialog, which) -> {
                    dialog.dismiss();
                });
                AlertDialog dialog = builder.create();

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        // Cambiar el color del texto del botón positivo a negro
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                        // Cambiar el color del texto del botón negativo a negro
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });

                dialog.show();
            }
        });



    }
    private void eliminarElemento(int position) {
        fichaItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, fichaItemList.size());
    }
    @Override
    public int getItemCount() {
        return fichaItemList.size();
    }

    public static class FichaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView,id;
        ImageButton editaButton;
        ImageButton borraButton;
        CardView ficha;

        public FichaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombre);
            editaButton = itemView.findViewById(R.id.edita);
            id = itemView.findViewById(R.id.idCatalogo);
            borraButton = itemView.findViewById(R.id.borra);
            ficha=itemView.findViewById(R.id.fichaGastos);
        }
    }
}
