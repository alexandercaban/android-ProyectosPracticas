package co.edu.univalle.sqliteexample.dominio.logica;

import java.util.List;

import co.edu.univalle.sqliteexample.dominio.accesodatos.ConexionDB;
import co.edu.univalle.sqliteexample.dominio.dto.DataBaseDTO;
import co.edu.univalle.sqliteexample.dominio.entidades.TipoUsuario;
import co.edu.univalle.sqliteexample.presentacion.IGenericPresenter;

public class TipoUsuarioLogic implements  ITipoUsuarioLogic {
    @Override
    public List<TipoUsuario> consultarTodosTipoUsuario(IGenericPresenter presenter) {
        ProcesadorSegundoPlano procesadorSegundoPlano = new ProcesadorSegundoPlano();
        DataBaseDTO<TipoUsuario> dataBaseDTO = new DataBaseDTO<TipoUsuario>();
        dataBaseDTO.setOperacion("find-all");
        dataBaseDTO.setGenericDAO(ConexionDB.getInstance().getTipoUsuarioDAO());
        dataBaseDTO.setGenericPresenter(presenter);
        procesadorSegundoPlano.execute(dataBaseDTO);

        return dataBaseDTO.getlEntidades();
    }
}
