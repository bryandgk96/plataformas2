package com.example.protnegocios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    String  tabla = "CREATE TABLE CLIENTE (ID INTEGER PRIMARY kEY, NOMBRE TEXT, APELLIDO TEXT, ESTADO TEXT)";
    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }
    // se ejecuta cuando tu subes version en la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table cliente");
        db.execSQL(tabla);
    }
}
