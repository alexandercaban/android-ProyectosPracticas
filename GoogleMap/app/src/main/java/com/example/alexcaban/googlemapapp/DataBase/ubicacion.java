package com.example.alexcaban.googlemapapp.DataBase;

/**
 * Created by Alex Caban on 11/06/2017.
 */

public class ubicacion {
    private int codigo;
    private String latitud, longitud, ubicacion;


    public ubicacion(int codigo, String latitud, String longitud, String ubicacion){
        this.codigo= codigo;
        this.latitud= latitud;
        this.longitud= longitud;
        this.ubicacion = ubicacion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }




}
