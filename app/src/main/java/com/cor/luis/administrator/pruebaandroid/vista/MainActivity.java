package com.cor.luis.administrator.pruebaandroid.vista;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudLogin;
import com.cor.luis.administrator.pruebaandroid.controlador.loggin.logginEvento;
import com.cor.luis.administrator.pruebaandroid.modelo.Login;
import com.cor.luis.administrator.pruebaandroid.vista.fragments.FragmentLogin;
import com.cor.luis.administrator.pruebaandroid.vista.fragments.FragmentProspecto;

import java.util.List;

public class MainActivity extends ActivityMenu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ConfiguraMenuBar();

        FragmentManager FM = this.getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();

        //se debe verificar si hay un login existente
        CrudLogin crudLogin = new CrudLogin(getApplicationContext());
        List<Object> logins = crudLogin.obtenerTodosLosItems();
        if(logins.size()>0)
        {
            logginEvento.insertaLog(getApplicationContext(),"Usuario no registrado");
            Login usuarioGrabado= (Login)logins.get(0);
            Fragment fragment = new FragmentProspecto();
            Bundle bundle = new Bundle();
            bundle.putString("TOKEN", usuarioGrabado.getToken());
            fragment.setArguments(bundle);

            FT.add(R.id.contentMag, fragment);
            FT.commit();
        }
        else
        {
            logginEvento.insertaLog(getApplicationContext(),"Usuario existente");
            Fragment fragment = new FragmentLogin();
            FT.add(R.id.contentMag, fragment);
            FT.commit();
        }

    }

    private void ConfiguraMenuBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

}
