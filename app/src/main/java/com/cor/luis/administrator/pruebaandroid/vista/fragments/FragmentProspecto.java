package com.cor.luis.administrator.pruebaandroid.vista.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.adapter.ProspectosAdapterList;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON.JsonProspectoParser;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON.JsonServicioProspecto;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudProspecto;
import com.cor.luis.administrator.pruebaandroid.controlador.loggin.logginEvento;
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

    private String token;
    private View view;
    private ProspectosAdapterList adaptador;

    public FragmentProspecto() {
        this.setToken("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setView(inflater.inflate(R.layout.fragment_prospecto, container, false));
        Bundle bundle = getArguments();

        if (bundle != null)
            setToken(bundle.getString("TOKEN"));

        ListView prospectoListView = (ListView) getView().findViewById(R.id.listViewProspecto);
        CrudProspecto crudProspecto = new CrudProspecto(getActivity().getApplicationContext());
        List<Object> prospectos = crudProspecto.obtenerTodosLosItems();

        if (prospectos.size() > 0) {
            setAdaptador(new ProspectosAdapterList(getActivity(), prospectos));
            logginEvento.insertaLog(getActivity().getApplicationContext(), "Se obtiene los prospectos de la base de datos");
        } else {
            this.getProspectos(crudProspecto);
            prospectos = crudProspecto.obtenerTodosLosItems();
        }


        prospectoListView.setAdapter(getAdaptador());

        final List<Object> finalProspectos = prospectos;
        prospectoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fragment fragment = new FragmentProspectoDetalle();
                Bundle bundle = new Bundle();

                Prospecto prospecto = (Prospecto) finalProspectos.get(position);
                bundle.putString("nombre", prospecto.getNombre());
                bundle.putString("apellido", prospecto.getApellido());
                bundle.putString("cedula", prospecto.getCedula());
                bundle.putString("telefono", prospecto.getTelefono());
                bundle.putString("estado", prospecto.getEstado() + "");


                fragment.setArguments(bundle);

                getActivity().getFragmentManager().beginTransaction().replace(R.id.contentMag, fragment).commit();

            }
        });

        return getView();
    }

    private void getProspectos(CrudProspecto crudProspecto) {
        List<Prospecto> prospectos1 = new ArrayList<>();
        try {
            JsonProspectoParser jsonProspectoParser = new JsonProspectoParser();
            JsonServicioProspecto json = new JsonServicioProspecto(getToken(), jsonProspectoParser);
            json.execute(new URL("http://directotesting.igapps.co/sch/prospects.json"));
            prospectos1 = json.get();

            crudProspecto.borrarTodosLosItems();
            crudProspecto.insertaLista(prospectos1);
            logginEvento.insertaLog(getActivity().getApplicationContext(), "Se obtienen los prospectos del servicio internet");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        setAdaptador(new ProspectosAdapterList(getActivity(), prospectos1));
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @android.support.annotation.Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ProspectosAdapterList getAdaptador() {
        return adaptador;
    }

    public void setAdaptador(ProspectosAdapterList adaptador) {
        this.adaptador = adaptador;
    }
}
