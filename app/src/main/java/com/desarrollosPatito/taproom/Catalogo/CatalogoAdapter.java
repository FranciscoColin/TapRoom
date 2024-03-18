package com.desarrollosPatito.taproom.Catalogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.desarrollosPatito.taproom.R;

import java.util.List;

public class CatalogoAdapter extends RecyclerView.Adapter<CatalogoAdapter.FichaViewHolder> {

    private Context context;
    private List<ObjCerveza> fichaItemList;

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
        holder.nombreTextView.setText(fichaItem.getBeerName());

        // Establecer eventos de clic para los botones
        holder.editaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Evento de clic para el botón de editar
                // Implementa aquí la lógica deseada para el botón de editar
            }
        });

        holder.borraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Evento de clic para el botón de borrar
                // Implementa aquí la lógica deseada para el botón de borrar
            }
        });
    }

    @Override
    public int getItemCount() {
        return fichaItemList.size();
    }

    public static class FichaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        ImageButton editaButton;
        ImageButton borraButton;

        public FichaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombre);
            editaButton = itemView.findViewById(R.id.edita);
            borraButton = itemView.findViewById(R.id.borra);
        }
    }
}
