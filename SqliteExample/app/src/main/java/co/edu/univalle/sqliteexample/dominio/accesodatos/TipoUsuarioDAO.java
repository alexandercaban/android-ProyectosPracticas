package co.edu.univalle.sqliteexample.dominio.accesodatos;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import java.util.List;
import co.edu.univalle.sqliteexample.dominio.entidades.TipoUsuario;
@Dao
public abstract class TipoUsuarioDAO extends GenericDAO<TipoUsuario>{
    @Query("SELECT * FROM TipoUsuario")
    public abstract List<TipoUsuario> consultarTodosTiposUsuarios();

}
