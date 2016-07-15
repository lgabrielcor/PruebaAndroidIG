package com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.loginJSON;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cor.luis.administrator.pruebaandroid.modelo.Login;

/**
 * Created by Administrator on 15/07/2016.
 */
public class JsonServicioLogin extends AsyncTask<URL, Void, Login> {
    HttpURLConnection con = null;

    @Override
    protected Login doInBackground(URL... params) {
        Login login = new Login();
        try{
            con = (HttpURLConnection)params[0].openConnection();
            con.setConnectTimeout(15000);
            con.setReadTimeout(10000);

            int statusCode = con.getResponseCode();

            if(statusCode!= 200){
                throw new Exception("Error en fuente de datos");
            }else{
                InputStream in = new BufferedInputStream(con.getInputStream());
                JsonLoginParser parser = new JsonLoginParser();
                login = parser.readJsonStream(in);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return login;
    }
}
