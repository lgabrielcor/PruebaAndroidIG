package com.cor.luis.administrator.pruebaandroid.vista.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudProspecto;
import com.cor.luis.administrator.pruebaandroid.controlador.loggin.logginEvento;
import com.cor.luis.administrator.pruebaandroid.modelo.Estado;
import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProspectoDetalle extends Fragment {

    boolean guardarbool = false;

    public FragmentProspectoDetalle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        logginEvento.insertaLog(getActivity().getApplicationContext(),"este es un evento");

        logginEvento.obtieneLog(getActivity().getApplicationContext());

        final View view = inflater.inflate(R.layout.fragment_prospecto_detalle, container, false);

        final Prospecto prospecto = new Prospecto();

        Bundle bundle = getArguments();

        prospecto.setNombre(bundle.getString("nombre"));
        prospecto.setApellido(bundle.getString("apellido"));
        prospecto.setCedula(bundle.getString("cedula"));
        prospecto.setTelefono(bundle.getString("telefono"));

        try {
            prospecto.setEstado(Integer.parseInt(bundle.getString("estado").trim()));
        } catch(NumberFormatException nfe) {
            Log.d("","Could not parse " + nfe);
        }


        final Button editar = (Button)view.findViewById(R.id.editarbtn);
        editar.setText("Editar");

        final Button volver = (Button)view.findViewById(R.id.volverbtn);
        final EditText nombre = (EditText)view.findViewById(R.id.nombretxt);
        final EditText apellido = (EditText)view.findViewById(R.id.apellidotxt);
        final EditText telefono = (EditText)view.findViewById(R.id.telefonotxt);
        final Spinner estadospn = (Spinner)view.findViewById(R.id.estadospn);

        nombre.setEnabled(false);
        apellido.setEnabled(false);
        telefono.setEnabled(false);
        telefono.setEnabled(false);


        nombre.setText(prospecto.getNombre());
        apellido.setText(prospecto.getApellido());
        telefono.setText(prospecto.getTelefono());
        estadospn.setSelection(prospecto.getEstado());


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!guardarbool)
                {
                    editar.setText("Guardar");
                    nombre.setEnabled(true);
                    apellido.setEnabled(true);
                    telefono.setEnabled(true);
                    estadospn.setEnabled(true);
                    guardarbool=true;
                }else{
                    editar.setText("Editar");
                    prospecto.setApellido(apellido.getText().toString());
                    prospecto.setNombre(nombre.getText().toString());
                    prospecto.setTelefono(telefono.getText().toString());

                    CrudProspecto crudProspecto = new CrudProspecto(getActivity().getApplicationContext());
                    crudProspecto.actualizarItem(prospecto,prospecto);

                    nombre.setEnabled(false);
                    apellido.setEnabled(false);
                    telefono.setEnabled(false);
                    telefono.setEnabled(false);

                    guardarbool=false;

                }
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentProspecto();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.contentMag, fragment).commit();
            }
        });

        configSpinner(estadospn);


        return view;
    }

    private void configSpinner(Spinner estadospn) {
        //lista de estados
        LinkedList estados = new LinkedList();
        estados.add("pending");
        estados.add("approved");
        estados.add("accepted");
        estados.add("rejected");
        estados.add("disabled");

        ArrayAdapter spinner_adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, estados);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadospn.setAdapter(spinner_adapter);
    }

}
