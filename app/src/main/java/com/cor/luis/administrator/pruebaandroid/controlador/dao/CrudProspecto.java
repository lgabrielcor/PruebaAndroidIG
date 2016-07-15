package com.cor.luis.administrator.pruebaandroid.controlador.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 15/07/2016.
 */
public class CrudProspecto extends SQLiteOpenHelper implements ICrud {

    String creadorProspecto= " CREATE TABLE `prospecto` (\n" +
            "\t`nombre`\tTEXT NOT NULL,\n" +
            "\t`apellido`\tTEXT NOT NULL\n" +
            "\t`cedula`\tTEXT NOT NULL,\n" +
            "\t`telefono`\tTEXT NOT NULL\n" +
            "\t`estado`\tNUMBER NOT NULL,\n" +
            ");";


    public CrudProspecto(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
