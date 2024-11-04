package com.myproyect.taller3.Saludo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.taller3.DB.UsuarioDBHelper;
import com.myproyect.taller3.R;


public class NameActivity extends AppCompatActivity {
    private EditText edtNombre;
    private Button btnGuardarNombre;
    private Button btnGuardarSQLite, btnCargarSQLite;
    private UsuarioDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        edtNombre = findViewById(R.id.edtNombre);
        btnGuardarNombre = findViewById(R.id.btnGuardarNombre);
        btnGuardarSQLite = findViewById(R.id.btnGuardarSQLite);
        btnCargarSQLite = findViewById(R.id.btnCargarSQLite);
        dbHelper = new UsuarioDBHelper(this);

        // Cargar el nombre guardado en SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        String nombreGuardado = prefs.getString("nombre", "");
        edtNombre.setText(nombreGuardado);

        btnGuardarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtNombre.getText().toString();
                SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", nombre);
                editor.apply();
                Toast.makeText(NameActivity.this, "Nombre guardado en SharedPreferences", Toast.LENGTH_SHORT).show();
            }
        });

        btnGuardarSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtNombre.getText().toString();
                dbHelper.guardarUsuario(nombre);
                Toast.makeText(NameActivity.this, "Nombre guardado en SQLite", Toast.LENGTH_SHORT).show();
            }
        });

        btnCargarSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = dbHelper.obtenerNombresUsuarios();
                Toast.makeText(NameActivity.this, "Usuarios: " + nombres, Toast.LENGTH_LONG).show();
            }
        });
    }
}
