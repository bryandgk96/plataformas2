package com.example.protnegocios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListadoMaestros extends AppCompatActivity {
    ListView listaMaestro;
    ArrayList<String> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_maestros);

        listaMaestro = (ListView) findViewById(R.id.simpleListViewMaestros);
        cargarListaMaestros();

        listaMaestro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListadoMaestros.this,"Posicion "+position,Toast.LENGTH_SHORT).show();
                int clave= Integer.parseInt(lista.get(position).split("\t")[0]);
                String nom = lista.get(position).split("\t")[1];
                String clie = lista.get(position).split("\t")[2];
                String zona = lista.get(position).split ("\t")[3];
                String est = lista.get(position).split ("\t")[4];

                if(est.equals("I")){
                    parent.getChildAt(position).setBackgroundColor(Color.RED);
                }
                Intent intent = new Intent(ListadoMaestros.this, ModificarMaestro.class);
                intent.putExtra("Id",clave);
                intent.putExtra("Nombre",nom);
                intent.putExtra("Cliente",clie);
                intent.putExtra("Zona",zona);
                intent.putExtra( "Estado", est);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                startActivity(new Intent(ListadoMaestros.this,Maestros.class));
                return (true);
            case R.id.reset:
                Toast.makeText(ListadoMaestros.this,"Reset obviar ",Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.about:
                Toast.makeText(ListadoMaestros.this,"aboue pronto",Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.exit:
                finish();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
    private void cargarListaMaestros(){
        lista = maestrosLista();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, lista);
        listaMaestro.setAdapter(adapter);
    }
    public ArrayList<String> maestrosLista(){
        ArrayList<String> datos = new ArrayList<>();
        DataBaseMaestros maestros = new DataBaseMaestros(this, "Demo3", null, 1);
        SQLiteDatabase db = maestros.getReadableDatabase();

        String sql = "select Id, Nombre, Cliente, Zona, Estado from maestro";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0)+"\t"+c.getString(1)+"\t"+c.getString(2)+"\t"+c.getString(3)+"\t"+c.getString(4);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }
    protected void onPostResume() {
        super.onPostResume();
        cargarListaMaestros();
    }
}
