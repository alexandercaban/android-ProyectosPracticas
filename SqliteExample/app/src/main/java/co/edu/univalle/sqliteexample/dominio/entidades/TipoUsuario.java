package co.edu.univalle.sqliteexample.dominio.entidades;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TipoUsuario {
    @ColumnInfo(name = "tuid")
    @PrimaryKey
    private Integer id;
    @ColumnInfo(name="tudescripcion")
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString (){
        return id+"-"+descripcion;
    }

}
