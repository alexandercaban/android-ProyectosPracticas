package co.edu.univalle.sqliteexample.dominio.dto;

import java.util.List;

import co.edu.univalle.sqliteexample.dominio.accesodatos.GenericDAO;
import co.edu.univalle.sqliteexample.presentacion.IGenericPresenter;

public class DataBaseDTO <T> {

    private T entidad;
    private GenericDAO genericDAO;
    private String operacion;
    private List<T> lEntidades;
    private Exception exception;
    private IGenericPresenter genericPresenter;

    public T getEntidad() {
        return entidad;
    }

    public void setEntidad(T entidad) {
        this.entidad = entidad;
    }

    public GenericDAO getGenericDAO() {
        return genericDAO;
    }

    public void setGenericDAO(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public List<T> getlEntidades() {
        return lEntidades;
    }

    public void setlEntidades(List<T> lEntidades) {
        this.lEntidades = lEntidades;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public IGenericPresenter getGenericPresenter() {
        return genericPresenter;
    }

    public void setGenericPresenter(IGenericPresenter genericPresenter) {
        this.genericPresenter = genericPresenter;
    }
}
