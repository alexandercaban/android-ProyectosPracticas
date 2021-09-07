package com.example.alexcaban.cine.Modelos;


public class ActorADT {
    private int nuCodigo, nuIdentificacion;
    private String strNombres, strApellidos, strEmail;

    public ActorADT(int nuCodigo, int nuIdentificacion, String strNombres, String strApellidos, String strEmail){
        this.nuCodigo =nuCodigo;
        this.nuIdentificacion = nuIdentificacion;
        this.strNombres = strNombres;
        this.strApellidos = strApellidos;
        this.strEmail = strEmail;
    }

    public int getNuIdentificacion() {
        return nuIdentificacion;
    }

    public void setNuIdentificacion(int nuIdentificacion) {
        this.nuIdentificacion = nuIdentificacion;
    }

    public int getNuCodigo() {
        return nuCodigo;
    }

    public void setNuCodigo(int nuCodigo) {
        this.nuCodigo = nuCodigo;
    }

    public String getStrNombres() {
        return strNombres;
    }

    public void setStrNombres(String strNombres) {
        this.strNombres = strNombres;
    }

    public String getStrApellidos() {
        return strApellidos;
    }

    public void setStrApellidos(String strApellidos) {
        this.strApellidos = strApellidos;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String toString(){
        return getNuCodigo()+"-"+ getStrNombres()+" "+ getStrApellidos();
    }
}
