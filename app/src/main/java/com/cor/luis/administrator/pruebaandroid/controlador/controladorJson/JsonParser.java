package com.cor.luis.administrator.pruebaandroid.controlador.controladorJson;

import android.support.annotation.NonNull;

import com.cor.luis.administrator.pruebaandroid.modelo.Login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 19/07/2016.
 */
public abstract class JsonParser {

    public abstract Object readJsonStream(InputStream in);

    @NonNull
    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
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
