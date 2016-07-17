package com.cor.luis.administrator.pruebaandroid.controlador.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.util.List;

/**
 * Created by luisgabrielcorredorcombita on 17/07/16.
 */
public class ProspectosAdapterList extends ArrayAdapter {
    public ProspectosAdapterList(Context context, List objects) {
        super(context, android.R.layout.two_line_list_item, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)getContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;


        if (null == convertView) { //Si no existe, entonces inflarlo
            view = inflater.inflate( R.layout.itemprospecto, parent, false);
        }

        TextView nombre = (TextView)view.findViewById(R.id.nombrelbl);
        TextView apellido = (TextView)view.findViewById(R.id.apellidolbl);
        TextView telefono = (TextView)view.findViewById(R.id.telefonolbl);
        TextView estado = (TextView)view.findViewById(R.id.estadolbl);
        TextView cedula=  (TextView)view.findViewById(R.id.cedulalbl);

        Prospecto prospecto = (Prospecto) getItem(position);

        apellido.setText("Nombre: "+prospecto.getApellido()+" ");
        nombre.setText(prospecto.getNombre());
        telefono.setText("Teléfono: "+prospecto.getTelefono());
        estado.setText("Estado: "+getEstadoNombre(prospecto.getEstado()));
        cedula.setText("Cédula: "+prospecto.getCedula());

        return view;
    }

    private String getEstadoNombre(int estado) {

        if(estado==0)
        {
            return "pending";

        }else if(estado == 1)
        {
            return "approved";
        }
        else if(estado == 2)
        {
            return "accepted";
        }
        else if(estado == 3)
        {
            return "rejected";
        }
        else
        {
            return "disabled";
        }
    }
}
