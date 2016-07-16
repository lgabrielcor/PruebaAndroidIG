package com.cor.luis.administrator.pruebaandroid.controlador.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cor.luis.administrator.pruebaandroid.modelo.Login;
import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.util.List;


/**
 * Created by Administrator on 15/07/2016.
 */
public class CrudLogin extends SQLiteOpenHelper implements ICrud {

    String creadorLogin= " CREATE TABLE `login` (\n" +
            "\t`email`\tTEXT NOT NULL,\n" +
            "\t`password`\tTEXT NOT NULL,\n" +
            "\t`token`\tTEXT NOT NULL\n" +
            ");";

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "cacheLogin.sqlite";
    SQLiteDatabase db;

    public CrudLogin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creadorLogin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public boolean insertar(Object o) {
        db = getWritableDatabase();
        db.delete("login", null, null);
        Login login = (Login)o;
        ContentValues valores = new ContentValues();
        valores.put("email", login.getEmail());
        valores.put("password", login.getPassword());
        valores.put("token", login.getToken());

        final long insertado = db.insert("login", null, valores);

        return insertado>-1?true:false;
    }

    @Override
    public Login ObtenerItem(String id) {
        SQLiteDatabase db = getReadableDatabase();

        Login login = new Login();
        Cursor c = db.query("login", null,
                null, null, null, null, null, null);

        c.moveToFirst();

        login.setEmail(c.getString(0));
        login.setPassword(c.getString(1));
        login.setToken(c.getString(2));

        db.close();
        c.close();


        return login;
    }

    @Override
    public List<Prospecto> obtenerTodosLosItems() {
        return null;
    }

    @Override
    public boolean actualizarItem(Object nuevo, Object anterior) {


        return false;
    }

    @Override
    public boolean borrarItem(Object elemento) {
        return false;
    }

    @Override
    public boolean borrarTodosLosItems() {
        return false;
    }
}
