// UsuarioDBHelper.java
package com.myproyect.taller3.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;

    public UsuarioDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sqlCreate = "CREATE TABLE usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT)";
        database.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(database);
    }

    public void guardarUsuario(String nombre) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        database.insert("usuarios", null, values);
        database.close();
    }

    public String obtenerNombresUsuarios() {
        List<String> listaNombres = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT nombre FROM usuarios", null);

        if (cursor.moveToFirst()) {
            do {
                listaNombres.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return listaNombres.toString();
    }
}
