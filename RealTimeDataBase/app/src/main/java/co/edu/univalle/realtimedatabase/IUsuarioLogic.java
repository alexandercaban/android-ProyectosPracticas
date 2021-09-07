package co.edu.univalle.realtimedatabase;

public interface IUsuarioLogic {
    void crearUsuario(String cedula, String clave,
                      String login, String nombre,
                      String tipoUsuario) throws Exception;

}
