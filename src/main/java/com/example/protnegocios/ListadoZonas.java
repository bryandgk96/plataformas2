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

public class ListadoZonas extends AppCompatActivity {
    ListView listView;
    ArrayList<String> zonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_zonas);
        setTitle("Registro de Zonas");
        listView = (ListView) findViewById(R.id.simpleListViewZonas);
        cargarListadoZonas();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListadoZonas.this,"Posicion "+position,Toast.LENGTH_SHORT).show();
                int clave= Integer.parseInt(zonas.get(position).split("\t")[0]);
                String nom = zonas.get(position).split("\t")[1];
                String est = zonas.get(position).split("\t")[2];
                if(est.equals("I")){
                    parent.getChildAt(position).setBackgroundColor(Color.RED);
                }
                Intent intent = new Intent(ListadoZonas.this, ModificarZonas.class);
                intent.putExtra("Id", clave);
                intent.putExtra("Zona", nom);
                intent.putExtra("Estado",est);
                startActivity(intent);

            }
        });

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
        /*if(item.getItemId() == android.R.id.home){
            finish();
        }*/
        switch (item.getItemId()){
            case R.id.add:
                startActivity(new Intent(ListadoZonas.this,ZonasMain.class));
                return (true);
            case R.id.reset:
                Toast.makeText(ListadoZonas.this,"Reset obviar ",Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.about:
                Toast.makeText(ListadoZonas.this,"aboue pronto",Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.exit:
                finish();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void cargarListadoZonas(){
        zonas = listaZonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, zonas);
        listView.setAdapter(adapter);
    }
    public ArrayList<String> listaZonas(){
        ArrayList<String> datos = new ArrayList<String>();
        DataBaseZonas helpZona = new DataBaseZonas(this, "Demo2", null,1);
        SQLiteDatabase db = helpZona.getReadableDatabase();

        String sql = "select Id, Zona, Estado from zona";
        Cursor c = db.rawQuery(sql, null);
        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0)+"\t"+c.getString(1)+"\t"+c.getString(2);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();

        return datos;
    }
    protected void onPostResume() {
        super.onPostResume();
        cargarListadoZonas();
    }
}
