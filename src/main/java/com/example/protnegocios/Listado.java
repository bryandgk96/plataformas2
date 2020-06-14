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

import java.util.ArrayList;


public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);

        listView = (ListView) findViewById(R.id.simpleListView);
        cargarListado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Listado.this,"Posicion "+position,Toast.LENGTH_SHORT).show();
                int clave= Integer.parseInt(listado.get(position).split("\t")[0]);
                String nom = listado.get(position).split("\t")[1];
                String ape = listado.get(position).split("\t")[2];
                String est = listado.get(position).split ("\t")[3];
                if(est.equals("I")){
                    parent.getChildAt(position).setBackgroundColor(Color.RED);
                }
                Intent intent = new Intent(Listado.this, Modificar.class);
                intent.putExtra("Id",clave);
                intent.putExtra("Nombre",nom);
                intent.putExtra("Apellido",ape);
                intent.putExtra( "Estado", est);
                startActivity(intent);
            }
        });

        //ClienteAdapter cliAdapter = new ClienteAdapter(this,listaClientes);
        //listView.setAdapter(cliAdapter);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
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
                startActivity(new Intent(Listado.this,Clientes.class));
                return (true);
            case R.id.reset:
                Toast.makeText(Listado.this,"Reset obviar ",Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.about:
                Toast.makeText(Listado.this,"aboue pronto",Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.exit:
                finish();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarListado(){
        listado = listaMaestros();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }

    public ArrayList<String> listaMaestros(){
        ArrayList<String> datos = new ArrayList<String>();
        DataBaseHelper helper = new DataBaseHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT Id, Nombre, Apellido, Estado FROM cliente ";
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0)+"\t"+c.getString(1)+"\t"+c.getString(2)+"\t"+c.getString(3);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }
}
