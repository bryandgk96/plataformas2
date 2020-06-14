package com.example.protnegocios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseZonas extends SQLiteOpenHelper {

    String tablaZonas = "CREATE TABLE zona (ID INTEGER PRIMARY KEY, ZONA TEXT, ESTADO TEXT)";

    public DataBaseZonas(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tablaZonas);
    }
    // se ejecuta cuando tu subes version en la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table zona");
        db.execSQL(tablaZonas);
    }
}
