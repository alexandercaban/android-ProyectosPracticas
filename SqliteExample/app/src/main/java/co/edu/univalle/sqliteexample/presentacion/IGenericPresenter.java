package co.edu.univalle.sqliteexample.presentacion;

import co.edu.univalle.sqliteexample.dominio.dto.DataBaseDTO;

public interface IGenericPresenter {
    void actualizarVista(DataBaseDTO dataBaseDTO);
    void notificar(DataBaseDTO dataBaseDTO);
}
