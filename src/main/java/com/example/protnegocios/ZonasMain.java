package com.example.protnegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZonasMain extends AppCompatActivity {

    EditText codigo;
    EditText zona;
    Button btnGuardar;
    Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_main);
        setTitle("Zonas");
        codigo = (EditText) findViewById(R.id.txtCodZona);
        zona = (EditText) findViewById(R.id.txtNombZona);
        btnGuardar = (Button) findViewById(R.id.btnGuardarZona);
        btnMostrar = (Button) findViewById(R.id.btnMostrarZonas);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //guardarZonas(Integer.parseInt(codigo.getText().toString()) , zona.getText().toString());
                registrarZonz(v);
                onBackPressed();
            }
        });
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ZonasMain.this,ListadoZonas.class));
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }

    private void guardarZonas(int _codigo, String _nombre){
        DataBaseZonas helpZona = new DataBaseZonas(this, "Demo2", null,1);
        SQLiteDatabase db = helpZona.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("Id",_codigo);
            c.put("Zona",_nombre);
            db.insert("zona", null,c);
            db.close();

            Toast.makeText(this, "Se registro Zona Correctamente", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    public void registrarZonz(View view ){
        DataBaseZonas helpZona = new DataBaseZonas(this, "Demo2", null,1);
        SQLiteDatabase db = helpZona.getWritableDatabase();
        String cod = codigo.getText().toString();
        String nom = zona.getText().toString();
        if(!cod.isEmpty() && !nom.isEmpty()){
            ContentValues c = new ContentValues();
            c.put("Id",cod);
            c.put("Zona",nom);
            c.put("Estado", "A");
            db.insert("zona", null,c);
            db.close();

            codigo.setText("");
            zona.setText("");

            Toast.makeText(this, "Se registro Zona Correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
}
