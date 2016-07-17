package com.cor.luis.administrator.pruebaandroid.vista;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.cor.luis.administrator.pruebaandroid.R;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudLogin;
import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudProspecto;
import com.cor.luis.administrator.pruebaandroid.vista.fragments.FragmentLogin;
import com.cor.luis.administrator.pruebaandroid.vista.fragments.FragmentProspecto;
import com.cor.luis.administrator.pruebaandroid.vista.fragments.logfragment;

/**
 * Created by luisgabrielcorredorcombita on 16/07/16.
 */
public class ActivityMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dashboard) {
            Fragment fragment = new logfragment();
            getFragmentManager().beginTransaction().replace(R.id.contentMag, fragment).commit();
        } else if (id == R.id.prospectos) {
            Fragment fragment = new FragmentProspecto();
            getFragmentManager().beginTransaction().replace(R.id.contentMag, fragment).commit();
        } else if (id == R.id.cerrarsesion) {
            CrudLogin crudLogin = new CrudLogin(this.getApplicationContext());
            CrudProspecto crudProspecto = new CrudProspecto(this.getApplicationContext());
            crudLogin.borrarTodosLosItems();
            crudProspecto.borrarTodosLosItems();

            Fragment fragment = new FragmentLogin();
            getFragmentManager().beginTransaction().replace(R.id.contentMag, fragment).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
