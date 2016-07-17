package com.cor.luis.administrator.pruebaandroid.vista.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.adapter.ProspectosAdapterList;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON.JsonServicioProspecto;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudProspecto;
import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProspecto extends Fragment {


    public FragmentProspecto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prospecto, container, false);
        Bundle bundle = getArguments();
        String token = bundle.getString("TOKEN");
        ListView prospectoListView = (ListView)view.findViewById(R.id.listViewProspecto);


        CrudProspecto crudProspecto = new CrudProspecto(getActivity().getApplicationContext());

        List<Object> prospectos = crudProspecto.obtenerTodosLosItems();

        ProspectosAdapterList adaptador;

        if(prospectos.size()>0){
            adaptador = new ProspectosAdapterList(getActivity(), prospectos);
        }else
        {
            List<Prospecto> prospectos1 = new ArrayList<>();
            try {
                JsonServicioProspecto json = new JsonServicioProspecto(token);
                json.execute(new URL("http://directotesting.igapps.co/sch/prospects.json"));
                prospectos1 = json.get();

                crudProspecto.borrarTodosLosItems();
                crudProspecto.insertaLista(prospectos1);
            }
            catch (MalformedURLException e) {
            e.printStackTrace();
        }
            catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
            adaptador = new ProspectosAdapterList(getActivity(), prospectos1);
        }

        prospectoListView.setAdapter(adaptador);

        prospectoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Log.d("se selecciona el item ", position+"");
            }
        });

        return view;
    }



}
