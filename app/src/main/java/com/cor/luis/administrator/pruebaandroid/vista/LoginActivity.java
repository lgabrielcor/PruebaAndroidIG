package com.cor.luis.administrator.pruebaandroid.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.loginJSON.JsonServicioLogin;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON.JsonServicioProspecto;
import com.cor.luis.administrator.pruebaandroid.modelo.Login;
import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            //URL url = new URL("http://directotesting.igapps.co/application/login?email=directo@directo.com&amp;password=directo123");

            URL url = new URL("http://directotesting.igapps.co/sch/prospects.json");
            JsonServicioProspecto json = new JsonServicioProspecto();

            json.execute(url);

            List<Prospecto> prospectos = json.get();

            Log.d("primer dato", prospectos.get(0).getApellido());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
