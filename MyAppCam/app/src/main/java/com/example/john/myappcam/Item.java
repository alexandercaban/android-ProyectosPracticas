package com.example.john.myappcam;


import android.graphics.Bitmap;

/**
 * Created by John on 06/05/2017.
 */

public class Item {

    private int image;
    private String fecha;

    public Bitmap getImagenCompleta() {
        return imagenCompleta;
    }

    public void setImagenCompleta(Bitmap imagenCompleta) {
        this.imagenCompleta = imagenCompleta;
    }

    private Bitmap imagenCompleta;


    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    private int opcion;

    public Item() {
        super();
    }

    public Item(int image, String fecha) {
        super();
        this.image = image;
        this.fecha = fecha;
        opcion = 1;
    }

    public Item(Bitmap imagen, String fecha) {
        super();
        this.fecha = fecha;
        opcion = 2;
        this.imagenCompleta = imagen;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
