// MainActivity.java
package com.myproyect.taller3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.taller3.DB.UsuarioDBHelper;
import com.myproyect.taller3.Settings.ConfigActivity;

public class MainActivity extends AppCompatActivity {
    private View layoutPrincipal;
    private EditText edtNombre;
    private TextView tvNombreMostrado;
    private UsuarioDBHelper dbHelper;
    private SharedPreferences prefs;
    private int colorFondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        layoutPrincipal = findViewById(R.id.layoutPrincipal);
        edtNombre = findViewById(R.id.edtNombre);
        tvNombreMostrado = findViewById(R.id.tvNombreMostrado);
        dbHelper = new UsuarioDBHelper(this);
        prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);

        cargarColorFondo();

        // Mostrar el nombre guardado
        String nombreGuardado = prefs.getString("nombre", "");
        edtNombre.setText(nombreGuardado);
        tvNombreMostrado.setText(nombreGuardado);

        // Botón para guardar nombre en SharedPreferences
        findViewById(R.id.btnGuardarNombre).setOnClickListener(view -> {
            String nombre = edtNombre.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombre", nombre);
            editor.apply();
            tvNombreMostrado.setText(nombre);
            Toast.makeText(MainActivity.this, "Nombre guardado", Toast.LENGTH_SHORT).show();
        });

        // Botón para guardar en SQLite
        findViewById(R.id.btnGuardarSQL).setOnClickListener(view -> {
            String nombre = edtNombre.getText().toString();
            dbHelper.guardarUsuario(nombre);
            Toast.makeText(MainActivity.this, "Nombre guardado en SQLite", Toast.LENGTH_SHORT).show();
        });
    }

    private void cargarColorFondo() {
        colorFondo = prefs.getInt("colorFondo", Color.WHITE);
        layoutPrincipal.setBackgroundColor(colorFondo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Manejar el clic en el ícono de configuración
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_config) {
            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        cargarColorFondo();
    }

}
