package com.desarrollosPatito.taproom.Catalogo;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.desarrollosPatito.taproom.ClienteTapRoom;
import com.desarrollosPatito.taproom.R;
import com.desarrollosPatito.taproom.Utilidades.Generales;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCatalogo extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiCatalogo apiService;
    Call<List<ObjCerveza>> call;
    Dialog dialogoCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activa);

        setup();
        events();
        cargarDatos();
    }

    private void setup() {
        recyclerView = findViewById(R.id.recyclerCatalogo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = ClienteTapRoom.getClient().create(ApiCatalogo.class);
    }

    private void events() {
        // Manejar eventos adicionales aquí si es necesario
    }



    private void cargarDatos() {
        dialogoCarga = Generales.mostrarDialogoCargaConImagen(this);

        call = apiService.getCervezas();
        call.enqueue(new Callback<List<ObjCerveza>>() {
            @Override
            public void onResponse(Call<List<ObjCerveza>> call, Response<List<ObjCerveza>> response) {
                if (response.isSuccessful()) {
                    List<ObjCerveza> beers = response.body();
                    // Crear y asignar el adaptador al RecyclerView
                    CatalogoAdapter adapter = new CatalogoAdapter(ActivityCatalogo.this, beers);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(ActivityCatalogo.this, "Datos cargados correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("API Call", "Error en la respuesta");
                }
                Generales.ocultarDialogoCargaConImagen(dialogoCarga);
            }

            @Override
            public void onFailure(Call<List<ObjCerveza>> call, Throwable t) {
                Log.e("API Call", "Error al realizar la solicitud", t);
                // Ocultar la actividad de carga en caso de fallo
                Generales.ocultarDialogoCargaConImagen(dialogoCarga);
            }
        });
    }

}
