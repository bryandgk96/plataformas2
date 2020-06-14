package com.example.protnegocios;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarZonas extends AppCompatActivity {

    EditText codigo;
    EditText nombre;
    Button btn_modificar, btn_eliminar;
    int clave;
    String nom, ape, est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_zonas);
        setTitle("Modificar Zona");
        Bundle b = getIntent().getExtras();
        if(b!=null){
            clave = b.getInt("Id");
            nom = b.getString("Zona");
            est = b.getString("Estado");
        }

        nombre = (EditText) findViewById(R.id.txtZonaNomMod);
        nombre.setText(nom);

        btn_modificar = (Button) findViewById(R.id.btnModificarZona);
        btn_eliminar = (Button) findViewById(R.id.btnEliminarZona);

        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarZona(clave,nombre.getText().toString());
                onBackPressed();
            }
        });
        final Context c = this;
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(c).setTitle("¿Desea eliminar registro?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ModificarZonas.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        eliminarZona(clave);
                        onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ModificarZonas.this, "no se elimino", Toast.LENGTH_SHORT).show();
                    }
                }).show();
//                eliminarZona(clave);
            }
        });

    }
    private void modificarZona(int _id ,String _nombre){
        DataBaseZonas helpZona = new DataBaseZonas(this, "Demo2", null,1);
        SQLiteDatabase db = helpZona.getReadableDatabase();

        String sql = "update zona set Zona='"+_nombre+"' where Id="+_id;
        db.execSQL(sql);
        db.close();

    }
    private void eliminarZona(int cod){
        DataBaseZonas helpZona = new DataBaseZonas(this, "Demo2", null,1);
        SQLiteDatabase db = helpZona.getReadableDatabase();
        //String sql = "delete from zona where Id="+cod;
        String sql = "";
        if(est.equals("I")){
            sql = "update zona set estado='A' where Id="+cod;
        }
        else{
            sql = "update zona set estado='I' where Id="+cod;
        }
        db.execSQL(sql);
        db.close();
    }
}
