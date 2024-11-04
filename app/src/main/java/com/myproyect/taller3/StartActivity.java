// StartActivity.java
package com.myproyect.taller3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class StartActivity extends AppCompatActivity {
    private TextView tvSaludo;
    private Button btnIrPrincipal;

    private View layoutStart;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvSaludo = findViewById(R.id.tvSaludo);
        btnIrPrincipal = findViewById(R.id.btnIrPrincipal);
        layoutStart = findViewById(R.id.layoutStart);


        // Obtener el nombre guardado en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        String nombreGuardado = prefs.getString("nombre", "");

        // Generar el saludo personalizado
        String saludo = obtenerSaludo() + (nombreGuardado.isEmpty() ? "" : ", " + nombreGuardado + "!");

        tvSaludo.setText(saludo);

        btnIrPrincipal.setOnClickListener(view -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private String obtenerSaludo() {
        int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hora >= 6 && hora < 12) {
            return "Buenos días";
        } else if (hora >= 12 && hora < 20) {
            return "Buenas tardes";
        } else {
            return "Buenas noches";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarColorFondo();
    }

    private void cargarColorFondo() {
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        int color = prefs.getInt("colorFondo", Color.WHITE);
        layoutStart.setBackgroundColor(color);
    }


}
