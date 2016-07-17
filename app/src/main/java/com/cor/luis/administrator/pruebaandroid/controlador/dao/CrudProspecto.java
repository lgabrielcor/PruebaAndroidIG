package com.cor.luis.administrator.pruebaandroid.controlador.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15/07/2016.
 */
public class CrudProspecto extends SQLiteOpenHelper implements ICrud {

    String creadorProspecto= " CREATE TABLE `prospecto` (\n" +
            "\t`nombre`\tTEXT NOT NULL,\n" +
            "\t`apellido`\tTEXT NOT NULL,\n" +
            "\t`cedula`\tTEXT NOT NULL,\n" +
            "\t`telefono`\tTEXT NOT NULL,\n" +
            "\t`estado`\tNUMBER NOT NULL\n" +
            ");";

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "cacheProspecto.sqlite";
    SQLiteDatabase db;

    public CrudProspecto(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public boolean insertar(Object o) {
        db = getWritableDatabase();
        Prospecto prospecto = (Prospecto)o;
        ContentValues valores = new ContentValues();
        valores.put("nombre", prospecto.getNombre());
        valores.put("apellido", prospecto.getApellido());
        valores.put("cedula", prospecto.getCedula());
        valores.put("telefono", prospecto.getTelefono());
        valores.put("estado", prospecto.getEstado());

        final long insertado = db.insert("prospecto", null, valores);

        return insertado>-1?true:false;

    }

    @Override
    public Prospecto ObtenerItem(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Prospecto prospecto = new Prospecto();

        String clave= "cedula = ?";
        String[] valor={id};

        Cursor c = db.query("prospecto",null,
                clave, valor, null, null, null, null);

        c.moveToFirst();

        prospecto.setApellido(c.getString(1));
        prospecto.setTelefono(c.getString(3));
        prospecto.setCedula(c.getString(2));
        prospecto.setEstado(c.getInt(4));
        prospecto.setNombre(c.getString(0));

        db.close();
        c.close();

        return prospecto;
    }

    @Override
    public List<Object> obtenerTodosLosItems() {
        List<Object> prospectos= new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("prospecto", null,
                null, null, null, null, null, null);


        if(c.getCount()>0) {
            c.moveToFirst();
            do {
                Prospecto prospecto = new Prospecto();

                prospecto.setApellido(c.getString(1));
                prospecto.setTelefono(c.getString(3));
                prospecto.setCedula(c.getString(2));
                prospecto.setEstado(c.getInt(4));
                prospecto.setNombre(c.getString(0));

                prospectos.add(prospecto);
            } while (c.moveToNext());
        }
        db.close();
        c.close();

        return prospectos;
    }

    @Override
    public boolean actualizarItem(Object nuevo, Object anterior) {

        db = getWritableDatabase();

        Prospecto prospecto = (Prospecto)nuevo;
        ContentValues valores = new ContentValues();
        valores.put("nombre", prospecto.getNombre());
        valores.put("apellido", prospecto.getApellido());
        valores.put("cedula", prospecto.getCedula());
        valores.put("telefono", prospecto.getTelefono());
        valores.put("estado", prospecto.getEstado());

        int result = db.update("prospecto", valores,"cedula="+((Prospecto)anterior).getCedula(), null);
        db.close();
        return result >= 1;
    }

    @Override
    public boolean borrarItem(Object elemento) {
        return false;
    }

    @Override
    public boolean borrarTodosLosItems() {
        db = getWritableDatabase();
        int result = db.delete("prospecto",null,null);
        db.close();
        return result >= 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creadorProspecto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
