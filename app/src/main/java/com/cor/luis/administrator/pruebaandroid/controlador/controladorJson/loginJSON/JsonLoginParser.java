package com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.loginJSON;

import com.cor.luis.administrator.pruebaandroid.modelo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 15/07/2016.
 */
public class JsonLoginParser {

    public Login readJsonStream(InputStream in) {
        Login login = new Login();

        try{
            String jsonstr = convertStreamToString(in);
            JSONObject reader = new JSONObject(jsonstr);

            if(jsonstr.contains("error"))
            {
                login.setToken("error");
            }
            else{

                login.setEmail(reader.getString("email"));
                login.setToken(reader.getString("authToken"));
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return login;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
