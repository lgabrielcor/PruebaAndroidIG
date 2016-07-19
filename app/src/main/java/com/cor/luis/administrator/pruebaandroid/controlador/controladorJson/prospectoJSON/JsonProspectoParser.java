package com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON;


import android.support.annotation.NonNull;

import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.JsonParser;
import com.cor.luis.administrator.pruebaandroid.modelo.Login;
import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luisgabrielcorredorcombita on 15/07/16.
 */
public class JsonProspectoParser extends JsonParser {

    @Override
    public List<Prospecto> readJsonStream(InputStream in) {

        List<Prospecto> prospectos = new ArrayList<>();

        Prospecto prospecto;
        try{
            JSONArray reader = new JSONArray(convertStreamToString(in));

            for (int i=0; i<reader.length(); i++){
                prospecto = new Prospecto();
                extraerProspecto(prospecto,reader,i);
                prospectos.add(prospecto);
            }

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return prospectos;
    }

    private void extraerProspecto(Prospecto prospecto, JSONArray entry, int i) throws JSONException  {
        JSONObject jsonObject = entry.getJSONObject(i);

        prospecto.setCedula(jsonObject.getString("id"));
        prospecto.setApellido(jsonObject.getString("surname"));
        prospecto.setNombre(jsonObject.getString("name"));
        prospecto.setEstado(jsonObject.getInt("statusCd"));
        prospecto.setTelefono(jsonObject.getString("telephone"));
    }

}
