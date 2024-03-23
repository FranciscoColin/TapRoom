package com.desarrollosPatito.taproom.Edita;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.desarrollosPatito.taproom.R;

public class ActivityEdit extends AppCompatActivity {
    EditText nombre, estilo, abv, ibu, descripcion, cerveceria, tamanio, precio, origen;
    Switch status;
    Button enviar;

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
        abv = findViewById(R.id.abvEdit);
        ibu = findViewById(R.id.ibuEdit);
        descripcion = findViewById(R.id.descripcionEdit);
        cerveceria = findViewById(R.id.cerveceriaEdit);
        tamanio = findViewById(R.id.tamanioEdit);
        precio = findViewById(R.id.precioEdit);
        origen = findViewById(R.id.origenEdit);
        status = findViewById(R.id.statusEdit);
        enviar = findViewById(R.id.enviarEdit);
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
        // Aquí puedes obtener los valores de los EditText y Switch y realizar las acciones necesarias
        String nombreCerveza = nombre.getText().toString();
        String estiloCerveza = estilo.getText().toString();
        // Obtener los valores de los demás campos según sea necesario

        // Aquí puedes realizar acciones como enviar los datos a un servidor, actualizar la base de datos local, etc.

        // Por ahora, solo mostraremos un mensaje de confirmación
        Toast.makeText(this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
    }
}
