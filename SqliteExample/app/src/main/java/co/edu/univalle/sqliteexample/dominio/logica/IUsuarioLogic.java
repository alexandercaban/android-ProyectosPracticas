package co.edu.univalle.sqliteexample.dominio.logica;

import java.util.List;

import co.edu.univalle.sqliteexample.dominio.entidades.Usuario;
import co.edu.univalle.sqliteexample.presentacion.IGenericPresenter;

public interface IUsuarioLogic {

    void crearUsuario(String cedula, String nombre, String apellido,
                      Integer tipoUsuario,
                      IGenericPresenter presenter) throws Exception;

    void consultarUsuarioCedula(String cedula,
                                   IGenericPresenter presenter) throws Exception;

    void modificarUsuario(String cedula, String nombre,
                          String Apellido,Integer tipoUsuario,
                          IGenericPresenter presenter) throws Exception;

    void eliminarUsuario(String cedula,
                          IGenericPresenter presenter) throws Exception;

    List<Usuario> consultarTodosUsuario(IGenericPresenter presenter);

}
