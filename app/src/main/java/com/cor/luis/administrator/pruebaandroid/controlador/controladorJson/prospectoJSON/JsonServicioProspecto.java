package com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON;

import android.os.AsyncTask;

import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 15/07/2016.
 */
public class JsonServicioProspecto extends AsyncTask<URL, Void, List<Prospecto>> {

    HttpURLConnection con = null;
    private String Tokenstr = "";

    private JsonProspectoParser jsonProspectoParser;

    public JsonServicioProspecto(String token, JsonProspectoParser jsonProspectoParser){
        setTokenstr(token);
        this.setJsonProspectoParser(jsonProspectoParser);
    }

    @Override
    protected List<Prospecto> doInBackground(URL... params) {

        List<Prospecto> prospectos=null;
        try{


            con = (HttpURLConnection)params[0].openConnection();

            con.setRequestProperty ("TOKEN", this.getTokenstr());

            con.setConnectTimeout(15000);
            con.setReadTimeout(10000);

            int statusCode = con.getResponseCode();

            if(statusCode!= 200){
                throw new Exception("Error en fuente de datos");
            }else{
                InputStream in = new BufferedInputStream(con.getInputStream());
                JsonProspectoParser parser= this.getJsonProspectoParser();
                prospectos=parser.readJsonStream(in);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }


        return prospectos;
    }

    public String getTokenstr() {
        return Tokenstr;
    }

    public void setTokenstr(String tokenstr) {
        Tokenstr = tokenstr;
    }

    public JsonProspectoParser getJsonProspectoParser() {
        return jsonProspectoParser;
    }

    public void setJsonProspectoParser(JsonProspectoParser jsonProspectoParser) {
        this.jsonProspectoParser = jsonProspectoParser;
    }
}
