package co.edu.univalle.sqliteexample.dominio.logica;

import java.util.List;

import co.edu.univalle.sqliteexample.R;
import co.edu.univalle.sqliteexample.dominio.accesodatos.ConexionDB;
import co.edu.univalle.sqliteexample.dominio.dto.DataBaseDTO;
import co.edu.univalle.sqliteexample.dominio.entidades.Usuario;
import co.edu.univalle.sqliteexample.presentacion.AppContext;
import co.edu.univalle.sqliteexample.presentacion.IGenericPresenter;

public class UsuarioLogic implements IUsuarioLogic {
    @Override
    public void crearUsuario(String cedula, String nombre,
                             String apellido, Integer tipoUsuario,
                             IGenericPresenter presenter) throws Exception {

        if(cedula == null || cedula.equals("")){
            throw  new Exception("La cédula del Usuario es obligatoria");
        }

        if(!cedula.matches("[0-9]*")){
            throw new Exception("La cédula del usuario debe"+
                                "ser un número entero positivo");
        }

        if(nombre==null || nombre.equals("")){
            throw new Exception("El valor del campo"+
                    AppContext.getContext().getResources().getString(R.string.lblNombre)+
                    "es obligatorio");
        }

        if(apellido==null || apellido.equals("")){
            throw new Exception("El valor del campo"+
                    "Apellido es obligatorio");
        }

        if(tipoUsuario==null || tipoUsuario.equals("")){
            throw new Exception("El valor del campo"+
                    "TipoUsuario es obligatorio");
        }

        /* creo la entidad con los valores ingresados por el usuario
           para ser persistido en sqlite
         */
        Usuario usuario = new Usuario();
        usuario.setCedula(new Integer(cedula));
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setIdTipoUsuario(tipoUsuario);

        /*
        * Creo la instancia DataBaseDTO para poder ser usada
        * por la clase que realiza operaciones en segundo plano
        * */

            DataBaseDTO<Usuario> dataBaseDTO = new DataBaseDTO<Usuario>();
            dataBaseDTO.setEntidad(usuario);
            dataBaseDTO.setGenericDAO(ConexionDB.getInstance().getUsuarioDAO());
            dataBaseDTO.setOperacion("insert");
            dataBaseDTO.setGenericPresenter(presenter);

            new ProcesadorSegundoPlano().execute(dataBaseDTO);

    }

    @Override
    public void consultarUsuarioCedula(String cedula,
                                          IGenericPresenter presenter) throws  Exception{
    try{

        if(cedula == null || cedula.equals("")){
            throw  new Exception("La cédula del Usuario es obligatoria");
        }

        if(!cedula.matches("[0-9]*")){
            throw new Exception("La cédula del usuario debe"+
                    "ser un número entero positivo");
        }
        Usuario usuario = new Usuario();
        usuario.setCedula(new Integer(cedula));

        DataBaseDTO<Usuario> dataBaseDTO = new DataBaseDTO<Usuario>();
        dataBaseDTO.setEntidad(usuario);
        dataBaseDTO.setGenericDAO(ConexionDB.getInstance().getUsuarioDAO());
        dataBaseDTO.setOperacion("findId");
        dataBaseDTO.setGenericPresenter(presenter);

        new ProcesadorSegundoPlano().execute(dataBaseDTO);


    }catch (Exception e){

    }


    }

    @Override
    public void modificarUsuario(String cedula, String nombre,
                                 String Apellido, Integer tipoUsuario,
                                 IGenericPresenter presenter) throws Exception {
        if(cedula == null || cedula.equals("")){
            throw  new Exception("La cédula del Usuario es obligatoria");
        }

        if(!cedula.matches("[0-9]*")){
            throw new Exception("La cédula del usuario debe"+
                    "ser un número entero positivo");
        }

        if(nombre==null || nombre.equals("")){
            throw new Exception("El valor del campo"+
                    AppContext.getContext().getResources().getString(R.string.lblNombre)+
                    "es obligatorio");
        }

        if(Apellido==null || Apellido.equals("")){
            throw new Exception("El valor del campo"+
                    "Apellido es obligatorio");
        }

        if(tipoUsuario==null || tipoUsuario.equals("")){
            throw new Exception("El valor del campo"+
                    "TipoUsuario es obligatorio");
        }

        /* creo la entidad con los valores ingresados por el usuario
           para ser persistido en sqlite
         */
        Usuario usuario = new Usuario();
        usuario.setCedula(new Integer(cedula));
        usuario.setNombre(nombre);
        usuario.setApellido(Apellido);
        usuario.setIdTipoUsuario(tipoUsuario);

        /*
         * Creo la instancia DataBaseDTO para poder ser usada
         * por la clase que realiza operaciones en segundo plano
         * */

        DataBaseDTO<Usuario> dataBaseDTO = new DataBaseDTO<Usuario>();
        dataBaseDTO.setEntidad(usuario);
        dataBaseDTO.setGenericDAO(ConexionDB.getInstance().getUsuarioDAO());
        dataBaseDTO.setOperacion("update");
        dataBaseDTO.setGenericPresenter(presenter);

        new ProcesadorSegundoPlano().execute(dataBaseDTO);


    }

    @Override
    public void eliminarUsuario(String cedula, IGenericPresenter presenter) throws Exception {
        if(cedula == null || cedula.equals("")){
            throw  new Exception("La cédula del Usuario es obligatoria");
        }

        if(!cedula.matches("[0-9]*")){
            throw new Exception("La cédula del usuario debe"+
                    "ser un número entero positivo");
        }
        Usuario usuario = new Usuario();
        usuario.setCedula(new Integer(cedula));

        DataBaseDTO<Usuario> dataBaseDTO = new DataBaseDTO<Usuario>();
        dataBaseDTO.setEntidad(usuario);
        dataBaseDTO.setGenericDAO(ConexionDB.getInstance().getUsuarioDAO());
        dataBaseDTO.setOperacion("delete");
        dataBaseDTO.setGenericPresenter(presenter);

        new ProcesadorSegundoPlano().execute(dataBaseDTO);

    }

    @Override
    public List<Usuario> consultarTodosUsuario(IGenericPresenter presenter) {

        Usuario usuario = new Usuario();

        DataBaseDTO<Usuario> dataBaseDTO = new DataBaseDTO<Usuario>();
        dataBaseDTO.setEntidad(usuario);
        dataBaseDTO.setGenericDAO(ConexionDB.getInstance().getUsuarioDAO());
        dataBaseDTO.setOperacion("find-all-users");
        dataBaseDTO.setGenericPresenter(presenter);

        new ProcesadorSegundoPlano().execute(dataBaseDTO);
        return dataBaseDTO.getlEntidades();
    }



}
