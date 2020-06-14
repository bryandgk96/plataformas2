package com.example.protnegocios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.zip.Inflater;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    public ClienteAdapter(Context context, List<Cliente> clientes) {
        super(context,0, clientes);
    }

    public View getView(int position, View convertView,ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.prueba_mostrar,parent,false
            );
        }

        Cliente currentClient = getItem(position);

        TextView codigo = (TextView) listItemView.findViewById(R.id.cliCod);
        codigo.setText(currentClient.getCod());

        TextView nombre = (TextView) listItemView.findViewById(R.id.cliNom);
        codigo.setText(currentClient.getNombre());

        TextView apellido = (TextView) listItemView.findViewById(R.id.cliApe);
        codigo.setText(currentClient.getApellido());

        return listItemView;
        //return super.getView(position, convertView, parent);
    }
}
