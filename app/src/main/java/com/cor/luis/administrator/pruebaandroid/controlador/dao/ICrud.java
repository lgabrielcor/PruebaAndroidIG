package com.cor.luis.administrator.pruebaandroid.controlador.dao;

import com.cor.luis.administrator.pruebaandroid.modelo.Prospecto;

import java.util.List;

/**
 * Created by Administrator on 15/07/2016.
 */
public interface ICrud {
    boolean insertar(Object o);
    Object ObtenerItem(String id);
    List<Object> obtenerTodosLosItems();
    boolean actualizarItem(Object nuevo, Object anterior);
    boolean borrarItem(Object elemento);
    boolean borrarTodosLosItems();
}
