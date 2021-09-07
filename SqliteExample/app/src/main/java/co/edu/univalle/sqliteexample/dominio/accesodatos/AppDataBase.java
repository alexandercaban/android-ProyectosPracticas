package co.edu.univalle.sqliteexample.dominio.accesodatos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import co.edu.univalle.sqliteexample.dominio.entidades.TipoUsuario;
import co.edu.univalle.sqliteexample.dominio.entidades.Usuario;

@Database(entities = {TipoUsuario.class, Usuario.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UsuarioDAO getUsuarioDAO();
    public abstract TipoUsuarioDAO getTipoUsuarioDAO();
}
