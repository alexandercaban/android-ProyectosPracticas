package co.edu.univalle.sqliteexample.dominio.logica;

import android.os.AsyncTask;

import co.edu.univalle.sqliteexample.dominio.accesodatos.TipoUsuarioDAO;
import co.edu.univalle.sqliteexample.dominio.accesodatos.UsuarioDAO;
import co.edu.univalle.sqliteexample.dominio.dto.DataBaseDTO;
import co.edu.univalle.sqliteexample.dominio.entidades.Usuario;

public class ProcesadorSegundoPlano extends AsyncTask<DataBaseDTO, Integer, DataBaseDTO> {

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param dataBaseDTO The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected DataBaseDTO doInBackground(DataBaseDTO... dataBaseDTO) {

        try {
            // 1. Identificar que Tipo de Entidad voy a trabajar
            UsuarioDAO usuarioDAO = dataBaseDTO[0].
                    getGenericDAO() instanceof UsuarioDAO ?
                    ((UsuarioDAO) dataBaseDTO[0].getGenericDAO()) : null;

            TipoUsuarioDAO tipoUsuarioDAO = dataBaseDTO[0].
                    getGenericDAO() instanceof TipoUsuarioDAO ?
                    ((TipoUsuarioDAO) dataBaseDTO[0].getGenericDAO()) : null;

            if (dataBaseDTO[0].getOperacion().equalsIgnoreCase("insert")) {
                usuarioDAO.crear((Usuario) dataBaseDTO[0].getEntidad());
            }

            if (dataBaseDTO[0].getOperacion().equalsIgnoreCase("update")) {
                usuarioDAO.actualizar((Usuario) dataBaseDTO[0].getEntidad());
            }

            if (dataBaseDTO[0].getOperacion().equalsIgnoreCase("delete")) {
                usuarioDAO.eliminar((Usuario) dataBaseDTO[0].getEntidad());
            }

            if (dataBaseDTO[0].getOperacion().equalsIgnoreCase("findId")) {
                Usuario usuarioConsultado = usuarioDAO.consultarUsuarioPorCedula(((Usuario) dataBaseDTO[0].getEntidad()).getCedula());
                dataBaseDTO[0].setEntidad(usuarioConsultado);
            }

            if (dataBaseDTO[0].getOperacion().equalsIgnoreCase("find-all-users")) {
                if(usuarioDAO != null){
                    dataBaseDTO[0].setlEntidades(usuarioDAO.consultarTodosUsuarios());
                }
            }

            if (dataBaseDTO[0].getOperacion().equalsIgnoreCase("find-all")) {
                if (tipoUsuarioDAO != null) {
                    dataBaseDTO[0].setlEntidades(tipoUsuarioDAO.consultarTodosTiposUsuarios());
                }
            }

        } catch (Exception exception) {
            dataBaseDTO[0].setException(exception);
        }
        return dataBaseDTO[0];
    }

    protected void onPostExecute(DataBaseDTO resultado) {

        if (resultado.getException() != null) {
            resultado.getGenericPresenter().notificar(resultado);
        } else {
            resultado.getGenericPresenter().actualizarVista(resultado);
        }
    }

}
