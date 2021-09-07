package com.example.alexcaban.cine.Modelos;

import java.io.Serializable;

/**
 * Created by Alex Caban on 9/03/2017.
 */

public class CarteleraADT implements Serializable{
    private String sbTituloPelicula;
    private String sbResumen;
    private int nuAnno;
    private int nuDuracion;
    private String sbActor;
    private String sbCategoria;


    public CarteleraADT(String sbTituloPelicula, String sbResumen, int nuAnno, int nuDuracion, String sbActor,String sbCategoria){
        this.sbTituloPelicula = sbTituloPelicula;
        this.sbResumen= sbResumen;
        this.nuAnno = nuAnno;
        this.nuDuracion = nuDuracion;
        this.sbActor = sbActor;
        this.sbCategoria = sbCategoria;
    }
    @Override
    public String toString() {
        return "sbTituloPelicula= " + sbTituloPelicula + ", sbResumen=" + sbResumen + ", nuAnno=" + nuAnno +", nuDuracion="+ nuDuracion +", sbActor=" + sbActor+", sbCategoria="+ sbCategoria;
    }

    public String getSbTituloPelicula() {
        return sbTituloPelicula;
    }

    public void setSbTituloPelicula(String sbTituloPelicula) {
        this.sbTituloPelicula = sbTituloPelicula;
    }

    public String getSbResumen() {
        return sbResumen;
    }

    public void setSbResumen(String sbResumen) {
        this.sbResumen = sbResumen;
    }

    public int getNuAnno() {
        return nuAnno;
    }

    public void setNuAnno(int nuAnno) {
        this.nuAnno = nuAnno;
    }

    public int getNuDuracion() {
        return nuDuracion;
    }

    public void setNuDuracion(int nuDuracion) {
        this.nuDuracion = nuDuracion;
    }

    public String getSbActor() {
        return sbActor;
    }

    public void setSbActor(String sbActor) {
        this.sbActor = sbActor;
    }

    public String getSbCategoria() {
        return sbCategoria;
    }

    public void setSbCategoria(String sbCategoria) {
        this.sbCategoria = sbCategoria;
    }
}
