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

public class Modificar extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    Button btn_modificar, btn_eliminar;
    int clave;
    String nom, ape,est;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b = getIntent().getExtras();
        if(b!=null){
            clave = b.getInt("Id");
            nom = b.getString("Nombre");
            ape = b.getString("Apellido");
            est = b.getString("Estado");
        }

        nombre = (EditText) findViewById(R.id.txt_Nombre);
        nombre.setText(nom);
        apellido = (EditText) findViewById(R.id.txt_Apellido);
        apellido.setText(ape);

        //nombre.setError();

        btn_modificar = (Button) findViewById(R.id.btnModificar);
        btn_eliminar = (Button) findViewById(R.id.btnEliminar);
        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(clave,nombre.getText().toString(),apellido.getText().toString());
                onBackPressed();
            }
        });
        final Context c = this;
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(c).setTitle("¿Desea eliminar registro?").setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Modificar.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        eliminar(clave);
                        onBackPressed();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Modificar.this, "no se elimino", Toast.LENGTH_SHORT).show();

                    }
                }).show();

            }
        });

    }
    private void modificar(int id, String _nombre, String _apellidos){
        DataBaseHelper helper = new DataBaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "update cliente set Nombre='"+_nombre+"', Apellido='"+_apellidos+"' where Id="+id;
        db.execSQL(sql);

        db.close();

    }
    private void eliminar(int id){
        DataBaseHelper helper = new DataBaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "";
        if(est.equals("I")){
            sql = "update cliente set estado='A' where Id="+id;
        }
        else{
            sql = "update cliente set estado='I' where Id="+id;
        }

        db.execSQL(sql);

        db.close();
    }
}
