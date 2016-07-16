package com.cor.luis.administrator.pruebaandroid.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.loginJSON.JsonServicioLogin;
import com.cor.luis.administrator.pruebaandroid.controlador.controladorJson.prospectoJSON.JsonServicioProspecto;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudLogin;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudProspecto;
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


            //jsonRequest();


        //crudLogineo();

        //crudProspecteo();
    }

    private void crudProspecteo() {
        CrudProspecto crud = new CrudProspecto(this.getApplicationContext());

        Prospecto prospecto = new Prospecto();
        prospecto.setNombre("111");
        prospecto.setEstado(1);
        prospecto.setCedula("222");
        prospecto.setTelefono("333");
        prospecto.setApellido("4444");


        Prospecto prospecto2 = new Prospecto();
        prospecto2.setNombre("111");
        prospecto2.setEstado(1);
        prospecto2.setCedula("222");
        prospecto2.setTelefono("14324325435432523");
        prospecto2.setApellido("4444");

        crud.insertar(prospecto);

        crud.ObtenerItem("222");

        crud.obtenerTodosLosItems();

        crud.actualizarItem(prospecto2, prospecto);

        prospecto = crud.ObtenerItem("222");

        crud.borrarTodosLosItems();
    }

    private void crudLogineo() {
        CrudLogin crud =new CrudLogin(this.getApplicationContext());
        Login login= new Login();
        login.setToken("1234");
        login.setPassword("pass");
        login.setEmail("email2");

        Log.d("funciona ", crud.insertar(login)?"si":"no");

        Login login1 = crud.ObtenerItem("email2");

        Log.d("funciona2 ",login1.getToken());
    }

    private void jsonRequest() throws MalformedURLException, InterruptedException, ExecutionException {
        //URL url = new URL("http://directotesting.igapps.co/application/login?email=directo@directo.com&amp;password=directo123");

        URL url = new URL("http://directotesting.igapps.co/sch/prospects.json");
        JsonServicioProspecto json = new JsonServicioProspecto();

        json.execute(url);

        List<Prospecto> prospectos = json.get();

        Log.d("primer dato", prospectos.get(0).getApellido());
    }
}
