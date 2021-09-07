package co.edu.univalle.sqliteexample.dominio.entidades;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Usuario {
    @PrimaryKey
    private Integer cedula;
    @ColumnInfo(name="usnombre")
    private String nombre;
    @ColumnInfo(name="usapellido")
    private String apellido;
    @ColumnInfo(name="usidtipousuario")
    private Integer idTipoUsuario;


    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

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

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String toString(){
        return "Usuario con cedula"+cedula+
                ",nombre"+nombre+
                ",apellido"+apellido;
    }

}
