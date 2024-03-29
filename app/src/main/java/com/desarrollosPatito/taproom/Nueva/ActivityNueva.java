package com.desarrollosPatito.taproom.Nueva;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.desarrollosPatito.taproom.R;
import com.desarrollosPatito.taproom.Utilidades.ClienteTapRoom;
import com.desarrollosPatito.taproom.Utilidades.Generales;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityNueva extends AppCompatActivity {

    EditText  nombre, estilo, abv, ibu, descripcion, cerveceria, tamanio, precio, origen;
    Switch status;
    Button enviar;

    ApiNueva apiService;
    Dialog dialogCarga;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.nueva);

        setup();
        events();
    }
    public void setup()
    {
        nombre=findViewById(R.id.beerName);
        estilo=findViewById(R.id.beerStyle);
        abv=findViewById(R.id.abv);
        ibu=findViewById(R.id.ibu);
        descripcion=findViewById(R.id.descripcion);
        cerveceria=findViewById(R.id.cerveceria);
        tamanio=findViewById(R.id.tamanio);
        precio=findViewById(R.id.precio);
        origen=findViewById(R.id.origen);
        status=findViewById(R.id.status);
        enviar=findViewById(R.id.enviar);

        apiService = ClienteTapRoom.getClient().create(ApiNueva.class);
    }
    public void events() {
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Generales.isNetworkConnected(ActivityNueva.this)) {
                    try {
                        // Validaciones
                        if(validarCampos()){
                            String nombreCerveza = nombre.getText().toString();
                            String estiloCerveza = estilo.getText().toString();
                            double abvCerveza = Double.parseDouble(abv.getText().toString());
                            int ibuCerveza = Integer.parseInt(ibu.getText().toString());
                            String descripcionCerveza = descripcion.getText().toString();
                            String cerveceriaCerveza = cerveceria.getText().toString();
                            String tamanioCerveza = tamanio.getText().toString();
                            double precioCerveza = Double.parseDouble(precio.getText().toString());
                            String origenCerveza = origen.getText().toString();
                            String estado = status.isChecked() ? "1" : "0";

                            insertBeer(nombreCerveza, estiloCerveza, abvCerveza, ibuCerveza, descripcionCerveza, cerveceriaCerveza, tamanioCerveza, precioCerveza, origenCerveza,estado);
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(ActivityNueva.this, "Por favor, verifica que todos los campos estén correctos.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityNueva.this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validarCampos() {
        // Aquí deberías incluir todas las validaciones necesarias, como ejemplos:
        boolean camposValidos = true;

        if (nombre.getText().toString().trim().isEmpty()) {
            nombre.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (estilo.getText().toString().trim().isEmpty()) {
            estilo.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (abv.getText().toString().trim().isEmpty()) {
            abv.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (ibu.getText().toString().trim().isEmpty()) {
            ibu.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (descripcion.getText().toString().trim().isEmpty()) {
            descripcion.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (cerveceria.getText().toString().trim().isEmpty()) {
            cerveceria.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (tamanio.getText().toString().trim().isEmpty()) {
            tamanio.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (precio.getText().toString().trim().isEmpty()) {
            precio.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (origen.getText().toString().trim().isEmpty()) {
            origen.setError("Este campo es obligatorio");
            camposValidos = false;
        }

        if (!camposValidos) {
            Toast.makeText(this, "Por favor complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
        }

        return camposValidos;
    }



    private void insertBeer(String beerName, String beerStyle, double abv, int ibu, String flavorDescription, String brewery, String servingSize, double price, String origin,String status) {

        dialogCarga = Generales.mostrarDialogoCargaConImagen(this);

        Call<ReponseNueva> call = apiService.insertBeer(beerName, beerStyle, abv, ibu, flavorDescription, brewery, servingSize, price, origin,status);
        call.enqueue(new Callback<ReponseNueva>() {
            @Override
            public void onResponse(Call<ReponseNueva> call, Response<ReponseNueva> response) {
                if (response.isSuccessful()) {
                    ReponseNueva apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getStatus().equals("success")) {
                        Toast.makeText(ActivityNueva.this, "Registro guardado correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ActivityNueva.this, "Error al insertar cerveza: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityNueva.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
                Generales.ocultarDialogoCargaConImagen(dialogCarga);
            }

            @Override
            public void onFailure(Call<ReponseNueva> call, Throwable t) {
                Toast.makeText(ActivityNueva.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Generales.ocultarDialogoCargaConImagen(dialogCarga);
            }
        });
    }

}
