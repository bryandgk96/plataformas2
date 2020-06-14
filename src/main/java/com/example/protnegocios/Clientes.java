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

public class Clientes extends AppCompatActivity {
    EditText nombre;
    EditText apellido;
    Button guardar;
    Button mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        setTitle("Clientes");
        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        guardar = (Button) findViewById(R.id.btnGuardar);
        mostrar = (Button) findViewById(R.id.btnMostrar);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCliente(v);
                onBackPressed();
            }
        });
        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Clientes.this, Listado.class));
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    public void guardarCliente(View view){
        DataBaseHelper helper = new DataBaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            String nom = nombre.getText().toString();
            String ape = apellido.getText().toString();
            if(!nom.isEmpty() && !ape.isEmpty()){
                ContentValues c = new ContentValues();
                c.put("Nombre",nom);
                c.put("Apellido",ape);
                c.put("Estado","A");
                db.insert("cliente", null,c);
                db.close();

                nombre.setText("");
                apellido.setText("");

                Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Deben llenar todos los campos", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
