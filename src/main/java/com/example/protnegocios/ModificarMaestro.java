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
import android.widget.Spinner;
import android.widget.Toast;

public class ModificarMaestro extends AppCompatActivity {

    EditText nombre,codigo;
    Spinner sp1,sp2;
    Button btn_modificar, btn_eliminar;
    int clave;
    String nom, ape,est, clie, zona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b = getIntent().getExtras();
        if(b!=null){
            clave = b.getInt("Id");
            nom = b.getString("Nombre");
            clie = b.getString("Cliente");
            zona = b.getString("Zona");
            est = b.getString("Estado");
        }
        final Maestros a ;
        codigo = (EditText) findViewById(R.id.txtCodigoModificarMaestro);
        codigo.setText(clave);
        nombre = (EditText) findViewById(R.id.txtNombreModificarMaestro);
        nombre.setText(nom);
        sp1 = (Spinner) findViewById(R.id.spinnerModificarClientes);

        sp2 = (Spinner) findViewById(R.id.spinnerModificarZonas);

        //nombre.setError();

        btn_modificar = (Button) findViewById(R.id.btnModificar);
        btn_eliminar = (Button) findViewById(R.id.btnEliminar);
        btn_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //modificar(clave,nombre.getText().toString(),sp1.getSelectedItem().toString());
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
                        Toast.makeText(ModificarMaestro.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        eliminar(clave);
                        onBackPressed();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ModificarMaestro.this, "no se elimino", Toast.LENGTH_SHORT).show();

                    }
                }).show();

            }
        });

    }
    private void modificar(int id, String _nombre, String _cliente, String _zona){
        DataBaseHelper helper = new DataBaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "update maestro set Nombre='"+_nombre+"', Cliente='"+_cliente+"', Zona='"+_zona+"' where Id="+id;
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
