package com.app.prueba.almacenamientolocalobject;

import java.io.Serializable;

/**
 * Created by CarlosOspina on 17/02/17.
 */

public class Persona implements Serializable{

    String nombre;
    String apellido;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
