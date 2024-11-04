// MainActivity.java
package com.myproyect.taller3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private View layoutPrincipal;
    private Button btnIrNombre, btnIrConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asegúrate de que el layout principal tenga el ID layoutPrincipal
        layoutPrincipal = findViewById(R.id.layoutPrincipal);
        btnIrNombre = findViewById(R.id.btnIrNombre);
        btnIrConfiguracion = findViewById(R.id.btnIrConfiguracion);

        cargarColorFondo();

        btnIrNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });

        btnIrConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cargarColorFondo() {
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        int color = prefs.getInt("colorFondo", Color.WHITE);
        layoutPrincipal.setBackgroundColor(color);
    }
}
