package com.example.alexcaban.cine.Modelos;

/**
 * Created by Alex Caban on 5/03/2017.
 */

public class CategoriaADT {

    private int nuCodigoCategoria;
    private String strDescripcionCategoria;

    public CategoriaADT(int nuCodigoCategoria, String strDescripcionCategoria){
        this.nuCodigoCategoria = nuCodigoCategoria;
        this.strDescripcionCategoria = strDescripcionCategoria;
    }

    public String getStrDescripcionCategoria() {
        return strDescripcionCategoria;
    }

    public void setStrDescripcionCategoria(String strDescripcionCategoria) {
        this.strDescripcionCategoria = strDescripcionCategoria;
    }

    public int getNuCodigoCategoria() {
        return nuCodigoCategoria;
    }

    public void setNuCodigoCategoria(int nuCodigoCategoria) {
        this.nuCodigoCategoria = nuCodigoCategoria;
    }

    public String toString() {
        return getStrDescripcionCategoria();
    }
}
