package com.cor.luis.administrator.pruebaandroid.controlador.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;


/**
 * Created by Administrator on 15/07/2016.
 */
public class CrudLogin extends SQLiteOpenHelper implements ICrud {

    String creadorLogin= " CREATE TABLE `login` (\n" +
            "\t`email`\tTEXT NOT NULL,\n" +
            "\t`password`\tTEXT NOT NULL\n" +
            "\t`token`\tTEXT NOT NULL\n" +
            ");";

    public CrudLogin(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public boolean insertar() {
        return false;
    }

    @Override
    public Object ObtenerItem(String id) {
        return null;
    }

    @Override
    public List<Object> obtenerTodosLosItems() {
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
