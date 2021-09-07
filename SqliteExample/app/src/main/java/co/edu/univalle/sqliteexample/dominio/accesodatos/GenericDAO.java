package co.edu.univalle.sqliteexample.dominio.accesodatos;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

public abstract class GenericDAO <T>{
    @Insert
    public abstract void crear(T entity);
    @Update
    public abstract void actualizar(T entity);
    @Delete
    public abstract void eliminar(T entity);
}
