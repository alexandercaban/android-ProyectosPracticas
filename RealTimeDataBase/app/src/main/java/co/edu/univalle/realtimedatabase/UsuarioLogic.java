package co.edu.univalle.realtimedatabase;

public class UsuarioLogic implements IUsuarioLogic {
    @Override
    public void crearUsuario(String cedula, String clave,
                             String login, String nombre,
                             String tipoUsuario) throws Exception {


       try{

           if(!cedula.matches("[0-9]*")){
               throw new Exception("La cédula no es un valor numérico");
           }

           if(cedula.length()!=10 && cedula.length()!=11){
               throw new Exception
                       ("La cédula debe tener entre 10 y 11 dígitos");
           }

           if(clave==null || clave.equals("")){
               throw new Exception
                       ("La clave es requerida para crear un usuario");
           }

           if(clave.length()<4){
               throw new Exception
                       ("La clave debe tener un mínimo de 4 dígitos");
           }

           if(login== null ||login.equals("")){
               throw new Exception
                       ("El login es requerido para crear un usuario");
           }

           if(nombre == null || nombre.equals("")){
               throw new Exception
                       ("El nombre es requerido para crear un usuario");
           }

           IUsuarioDAO iUsuarioDAO= new UsuarioDAO();
           iUsuarioDAO.crearUsuario(new UsuarioDTO(new Long(cedula),clave,
                                                login,nombre,
                                        new Long(tipoUsuario)));

       }catch(Exception exception){
            throw exception;
       }



    }
}
