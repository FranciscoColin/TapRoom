package com.desarrollosPatito.taproom.Catalogo.Edita;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.desarrollosPatito.taproom.Catalogo.ActivityCatalogo;
import com.desarrollosPatito.taproom.Catalogo.ApiCatalogo;
import com.desarrollosPatito.taproom.MainActivity;
import com.desarrollosPatito.taproom.Nueva.ActivityNueva;
import com.desarrollosPatito.taproom.R;
import com.desarrollosPatito.taproom.Utilidades.ClienteTapRoom;
import com.desarrollosPatito.taproom.Utilidades.Generales;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ActivityEdit extends AppCompatActivity {
    EditText nombre, estilo, abv, ibu, descripcion, cerveceria, tamanio, precio, origen;
    Switch status;
    Button enviar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualiza);

        setup();
        events();
    }

    private void setup() {
        nombre = findViewById(R.id.beerNameEdit);
        estilo = findViewById(R.id.beerStyleEdit);
        descripcion = findViewById(R.id.descripcionEdit);
        cerveceria = findViewById(R.id.cerveceriaEdit);
        tamanio = findViewById(R.id.tamanioEdit);
        precio = findViewById(R.id.precioEdit);
        origen = findViewById(R.id.origenEdit);
        status = findViewById(R.id.statusEdit);
        enviar = findViewById(R.id.enviarEdit);
        abv = findViewById(R.id.abvEdit);
        ibu = findViewById(R.id.ibuEdit);



        populateFieldsFromIntent();
    }

    private void events() {
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se manejará el evento de clic en el botón "enviar"
                enviarDatos();
            }
        });
    }

    private void enviarDatos() {
        if(validarCampos()) {
            if (Generales.isNetworkConnected(ActivityEdit.this)) {
                // Recopilación de datos desde la UI
                String idData = getIntent().getStringExtra("id");
                String nombreCerveza = nombre.getText().toString();
                String estiloCerveza = estilo.getText().toString();
                String abvValue = abv.getText().toString();
                String ibuValue = ibu.getText().toString();
                String descripcionValue = descripcion.getText().toString();
                String cerveceriaValue = cerveceria.getText().toString();
                String tamanioValue = tamanio.getText().toString();
                String precioValue = precio.getText().toString();
                String origenValue = origen.getText().toString();
                String statusValue = status.isChecked() ? "1" : "0";

                // Obtener instancia de Retrofit y hacer la llamada al endpoint
                Retrofit retrofit = ClienteTapRoom.getClient();
                ApiCatalogo apiService = retrofit.create(ApiCatalogo.class);
                Call<ResponseEdit> call = apiService.updateBeer(idData, nombreCerveza, estiloCerveza, abvValue, ibuValue, descripcionValue,
                        cerveceriaValue, tamanioValue, precioValue, origenValue, statusValue);

                call.enqueue(new Callback<ResponseEdit>() {
                    @Override
                    public void onResponse(Call<ResponseEdit> call, Response<ResponseEdit> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(ActivityEdit.this, "Registro actualizado correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                            intent = new Intent(ActivityEdit.this, ActivityCatalogo.class);
                            startActivity(intent);
                        } else {
                            try {
                                Toast.makeText(ActivityEdit.this, "Error: " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEdit> call, Throwable t) {
                        Log.e("API_FAILURE", "Fallo en la llamada: " + t.getMessage());
                    }
                });
            } else {
                Toast.makeText(ActivityEdit.this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
            }

        }
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
    private void populateFieldsFromIntent() {
        Intent intent = getIntent();

        if(intent != null) {
            String abvValue = intent.getStringExtra("abv");
            String precioValue = intent.getStringExtra("precio");
            String ibuValue = intent.getStringExtra("ibu");

            String nombreValue = intent.getStringExtra("nombre");
            String estiloValue = intent.getStringExtra("estilo");
            String descripcionValue = intent.getStringExtra("descripcion");
            String casaValue = intent.getStringExtra("casa");
            String tamanioValue = intent.getStringExtra("tamanio");
            String origenValue = intent.getStringExtra("origen");
            String statusValue = intent.getStringExtra("status");

            nombre.setText(nombreValue);
            estilo.setText(estiloValue);
            abv.setText(String.valueOf(abvValue));
            ibu.setText(String.valueOf(ibuValue));
            precio.setText(String.valueOf(precioValue));
            descripcion.setText(descripcionValue);
            cerveceria.setText(casaValue);
            tamanio.setText(tamanioValue);
            origen.setText(origenValue);

            if(statusValue != null && !statusValue.isEmpty()) {
                int statusIntValue = Integer.parseInt(statusValue);
                status.setChecked(statusIntValue == 1);
            }
        }
    }



}
