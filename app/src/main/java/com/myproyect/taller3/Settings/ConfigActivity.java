// ConfigActivity.java
package com.myproyect.taller3.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.taller3.R;
import com.myproyect.taller3.StartActivity;

public class ConfigActivity extends AppCompatActivity {
    private View layoutConfig;
    private Button btnColorRojo, btnColorVerde, btnVolverInicio;
    private SharedPreferences prefs;
    private int colorFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        layoutConfig = findViewById(R.id.layoutConfig);
        btnColorRojo = findViewById(R.id.btnColorRojo);
        btnColorVerde = findViewById(R.id.btnColorVerde);
        btnVolverInicio = findViewById(R.id.btnVolverInicio);
        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);

        cargarColorFondo();

        btnColorRojo.setOnClickListener(view -> cambiarColorFondo(Color.RED));

        btnColorVerde.setOnClickListener(view -> cambiarColorFondo(Color.GREEN));

        btnVolverInicio.setOnClickListener(view -> {
            Intent intent = new Intent(ConfigActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void cambiarColorFondo(int color) {
        colorFondo = color;
        layoutConfig.setBackgroundColor(colorFondo);
        // Guardar en SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("colorFondo", colorFondo);
        editor.apply();
    }

    private void cargarColorFondo() {
        colorFondo = prefs.getInt("colorFondo", Color.WHITE);
        layoutConfig.setBackgroundColor(colorFondo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarColorFondo();
    }
}
