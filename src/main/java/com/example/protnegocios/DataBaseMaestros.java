package com.example.protnegocios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseMaestros extends SQLiteOpenHelper {
    String tablaMaestros = "CREATE TABLE maestro (ID INTEGER PRIMARY KEY, nombre TEXT, cliente TEXT, zona TEXT, estado TEXT)";

    public DataBaseMaestros(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tablaMaestros);
    }
    // se ejecuta cuando tu subes version en la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table maestro");
        db.execSQL(tablaMaestros);
    }
}
