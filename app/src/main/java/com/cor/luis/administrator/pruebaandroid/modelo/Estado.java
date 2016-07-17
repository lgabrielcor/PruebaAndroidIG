package com.cor.luis.administrator.pruebaandroid.modelo;

/**
 * Created by luisgabrielcorredorcombita on 17/07/16.
 */
public class Estado {

    private int id;
    private String nombre;

    public Estado(int id, String nombre){
        id=id;
        nombre=nombre;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
