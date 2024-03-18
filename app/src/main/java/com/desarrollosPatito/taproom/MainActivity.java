package com.desarrollosPatito.taproom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.desarrollosPatito.taproom.R;
import com.desarrollosPatito.taproom.Catalogo.ActivityCatalogo;
import com.desarrollosPatito.taproom.Nueva.ActivityNueva;

public class MainActivity extends AppCompatActivity {
    Button nueva, catalogo;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setup();
        events();
    }

    private void setup() {
        nueva = findViewById(R.id.nueva);
        catalogo = findViewById(R.id.catalogo);

    }

    private void events() {
        nueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ActivityNueva.class);

                startActivity(intent);
            }
        });

        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ActivityCatalogo.class);

                startActivity(intent);
            }
        });
    }
}
