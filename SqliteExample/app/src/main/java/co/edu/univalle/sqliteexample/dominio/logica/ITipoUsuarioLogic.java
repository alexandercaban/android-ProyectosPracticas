package co.edu.univalle.sqliteexample.dominio.logica;

import java.util.List;

import co.edu.univalle.sqliteexample.dominio.entidades.TipoUsuario;
import co.edu.univalle.sqliteexample.presentacion.IGenericPresenter;

public interface ITipoUsuarioLogic {
    List<TipoUsuario> consultarTodosTipoUsuario(IGenericPresenter presenter);
}
