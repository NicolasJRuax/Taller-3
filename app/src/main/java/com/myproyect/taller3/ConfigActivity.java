package com.myproyect.taller3;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigActivity extends AppCompatActivity {
    private View layoutConfig;
    private Button btnColorRojo, btnColorVerde, btnVolverInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        layoutConfig = findViewById(R.id.layoutConfig);
        btnColorRojo = findViewById(R.id.btnColorRojo);
        btnColorVerde = findViewById(R.id.btnColorVerde);
        btnVolverInicio = findViewById(R.id.btnVolverInicio);

        cargarColorFondo();

        btnColorRojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarColorFondo(Color.RED);
            }
        });

        btnColorVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarColorFondo(Color.GREEN);
            }
        });

        btnVolverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void cambiarColorFondo(int color) {
        layoutConfig.setBackgroundColor(color);
        // Guardar en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("colorFondo", color);
        editor.apply();
    }

    private void cargarColorFondo() {
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        int color = prefs.getInt("colorFondo", Color.WHITE);
        layoutConfig.setBackgroundColor(color);
    }
}
