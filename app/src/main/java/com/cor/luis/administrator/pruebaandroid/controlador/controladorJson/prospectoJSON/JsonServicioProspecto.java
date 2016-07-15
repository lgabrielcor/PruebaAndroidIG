package com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON;

import android.os.AsyncTask;

import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 15/07/2016.
 */
public class JsonServicioProspecto extends AsyncTask<URL, Void, List<Prospecto>> {

    //TODO: pasar los parametros de TOKEN y obtener la informacion de los prospectos
    @Override
    protected List<Prospecto> doInBackground(URL... params) {
        return null;
    }
}
