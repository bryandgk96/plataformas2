package com.example.protnegocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Maestros extends AppCompatActivity {
    EditText codMaestro;
    EditText nomMaestro;
    Spinner spinerCliente;
    Spinner spinerZona;
    Button btn_guardarMaestro;
    Button btn_mostrarMaestros;
    ArrayList<String> listaClientes;
    ArrayList<String> listaZonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestros);
        setTitle("Agregar Maestro de Publicidad");

        codMaestro = (EditText) findViewById(R.id.txtCodigoMaestro);
        nomMaestro = (EditText) findViewById(R.id.txtNombreMaestro);
        spinerCliente = (Spinner) findViewById(R.id.spinnerClientes);
        spinerZona = (Spinner) findViewById(R.id.spinnerZonas);
        cargarClientesSpinner();
        cargarZonasSpinner();
        btn_guardarMaestro = (Button) findViewById(R.id.btnGuardarMaestro);
        btn_mostrarMaestros = (Button) findViewById(R.id.btnMostrarMaestros);
        btn_guardarMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarMaestro(v);
                onBackPressed();
            }
        });
        btn_mostrarMaestros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Maestros.this,ListadoMaestros.class));
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }

    public void registrarMaestro(View view){

        DataBaseMaestros helpMaestro = new DataBaseMaestros(this, "Demo3", null,1);
        SQLiteDatabase db = helpMaestro.getWritableDatabase();
        String cod = codMaestro.getText().toString();
        String nom = nomMaestro.getText().toString();
        String selectCliente = spinerCliente.getSelectedItem().toString() ;
        String selectZona = spinerZona.getSelectedItem().toString();
        if(!cod.isEmpty() && !nom.isEmpty() && !selectCliente.isEmpty() && !selectZona.isEmpty()){
            ContentValues c = new ContentValues();
            c.put("Id",cod);
            c.put("Nombre",nom);
            c.put("Cliente",selectCliente);
            c.put("Zona",selectZona);
            c.put("Estado", "A");
            db.insert("maestro", null, c);
            db.close();

            codMaestro.setText("");
            nomMaestro.setText("");
            //spinerCliente.setSelected(false);
            //spinerZona.setSelected(false);


            Toast.makeText(this, "Se registro Correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            db.close();
        }

    }
    public void cargarClientesSpinner(){
        listaClientes = obtenerCliente();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaClientes);
        spinerCliente.setAdapter(adapter1);
    }
    public void cargarZonasSpinner(){
        listaZonas = obtenerZonas();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaZonas);
        spinerZona.setAdapter(adapter2);
    }
    private ArrayList<String> obtenerCliente(){
        ArrayList<String> datosCli = new ArrayList<String>();
        DataBaseHelper helper = new DataBaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT Nombre, Apellido FROM cliente where Estado='A'";
        Cursor c = db.rawQuery(sql,null);
        try{
            Log.e("kappa", "obtenerCliente: "+c.getColumnNames() );

            if(c.moveToFirst()){
                do{
                    Log.e("kappa", "obtenerCliente: "+c.getColumnName(0) + " " +c.getColumnCount() );
                    Log.e("kappa", c.getString(c.getColumnIndex("NOMBRE")) );
                    Log.e("kappa", c.getString(c.getColumnIndex("APELLIDO")) );
                    String nombre = c.getString(c.getColumnIndex("NOMBRE"));
                    String ape=  c.getString(c.getColumnIndex("APELLIDO"));
                    //String linea = c.getInt(0)+" "+c.getString(1)+" "+c.getString(2);
                    //String linea2 = linea.substring(linea.indexOf(" ")+1, linea.length() );

                    datosCli.add(nombre+" "+ape);
                }while(c.moveToNext());
            }
        }catch (Exception e){
            Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
        }
        db.close();
        return datosCli;
    }
    private ArrayList<String> obtenerZonas(){
        ArrayList<String> datosZon = new ArrayList<String>();
        DataBaseZonas helpZona = new DataBaseZonas(this, "Demo2", null,1);
        SQLiteDatabase db = helpZona.getReadableDatabase();

        String sql = "select Zona from zona where Estado='A'";
        Cursor c = db.rawQuery(sql, null);
        try{

            if(c.moveToFirst()){
                do{
                    String nomZona = c.getString(c.getColumnIndex("ZONA"));
                    datosZon.add(nomZona);
                }while(c.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();

        return datosZon;
    }
}
