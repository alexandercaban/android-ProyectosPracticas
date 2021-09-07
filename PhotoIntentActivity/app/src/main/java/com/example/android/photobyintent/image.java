package com.example.android.photobyintent;

import android.graphics.Bitmap;

/**
 * Created by Alex Caban on 20/05/2017.
 */

public class image {
    Bitmap imagenes;
    String nombre;

    public image(Bitmap imagen , String nombre){
        this.imagenes = imagen;
        this.nombre = nombre;
    }

    public Bitmap getImagenes() {
        return imagenes;
    }

    public void setImagenes(Bitmap imagenes) {
        this.imagenes = imagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
