package com.cor.luis.administrator.pruebaandroid;

import android.content.Context;
import android.test.AndroidTestCase;

import com.cor.luis.administrator.pruebaandroid.controlador.dao.CrudLogin;
import com.cor.luis.administrator.pruebaandroid.modelo.Login;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends AndroidTestCase{


    @Test
    public void probarInsertarLogin(){

        CrudLogin crud =new CrudLogin(getContext());
        Login login= new Login();
        login.setToken("1234");
        login.setPassword("pass");
        login.setEmail("email2");

        assertTrue("insertado", crud.insertar(login));
    }
}