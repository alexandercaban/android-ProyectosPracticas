package co.edu.univalle.sqliteexample.dominio.accesodatos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import co.edu.univalle.sqliteexample.dominio.entidades.Usuario;
@Dao
public abstract class UsuarioDAO extends GenericDAO<Usuario> {
    @Query("SELECT * FROM Usuario WHERE cedula = :cedula")
    public abstract Usuario consultarUsuarioPorCedula(Integer cedula);

    @Query("SELECT * FROM Usuario")
    public abstract List<Usuario> consultarTodosUsuarios();

}
