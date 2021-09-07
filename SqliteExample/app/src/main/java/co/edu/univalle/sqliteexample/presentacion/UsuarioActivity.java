package co.edu.univalle.sqliteexample.presentacion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import co.edu.univalle.sqliteexample.R;
import co.edu.univalle.sqliteexample.dominio.dto.DataBaseDTO;
import co.edu.univalle.sqliteexample.dominio.entidades.TipoUsuario;
import co.edu.univalle.sqliteexample.dominio.entidades.Usuario;
import co.edu.univalle.sqliteexample.dominio.logica.ITipoUsuarioLogic;
import co.edu.univalle.sqliteexample.dominio.logica.IUsuarioLogic;
import co.edu.univalle.sqliteexample.dominio.logica.TipoUsuarioLogic;
import co.edu.univalle.sqliteexample.dominio.logica.UsuarioLogic;

/*@author:
 *@description:
 */
public class UsuarioActivity extends AppCompatActivity implements IGenericPresenter {

    private Spinner spTipoUsuario;
    private ArrayAdapter<TipoUsuario> adapterTipoUsuario;
    private EditText txtCedula;
    private EditText txtNombre;
    private EditText txtApellido;
    private FloatingActionButton btnCrear;
    private FloatingActionButton btnModificar;
    private FloatingActionButton btnEliminar;
    private FloatingActionButton btnLimpiar;
    private RecyclerView rvUsuarios;
    private usuariosAdapter adaptador;
    private ArrayList<Usuario> usuario;

    public void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_usuario);
        spTipoUsuario = findViewById(R.id.spTipoUsuario);
        btnCrear = findViewById(R.id.btnCrear);
        btnModificar = findViewById(R.id.btnModificar);
        btnEliminar = findViewById(R.id.btnElimimnar);
        btnLimpiar = findViewById(R.id.btnLimpiar);
        txtCedula = findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        rvUsuarios = findViewById(R.id.recyclerUsuario);
        rvUsuarios.setLayoutManager(new LinearLayoutManager(this));


        deshabilitarTodosBotones();
        //deshablitarCamposDeEntrada();
        consultarTipoUsuarios();
        consultarTodosLosUsuarios();

        txtCedula.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && txtCedula!=null &&
                                !txtCedula.getText().toString().equals("")){
                    consultarUsuario();
                }
            }
        });

    }

    public void consultarTodosLosUsuarios(){
        try{
            IUsuarioLogic iUsuarioLogic = new UsuarioLogic();
            iUsuarioLogic.consultarTodosUsuario(this);
            usuario = new ArrayList<>();
            Usuario user = new Usuario();
            usuario.add(user);
            adaptador = new usuariosAdapter(usuario);
            rvUsuarios.setAdapter(adaptador);

        }catch(Exception e){
            Toast.makeText(this,"Error: "+ e,Toast.LENGTH_LONG).show();
        }
    }

    public void consultarUsuario(){
        try{
            IUsuarioLogic iUsuarioLogic = new UsuarioLogic();
            iUsuarioLogic.consultarUsuarioCedula(txtCedula.getText().toString(),
                    this);
            btnCrear.setEnabled(true);
            btnModificar.setEnabled(true);
            btnEliminar.setEnabled(true);

        }catch(Exception e){

        }
    }

    public void accionCrearUsuario(View view){
            try{
                Integer idTipoUsuario=null;

                if(spTipoUsuario.getSelectedItem()!=null){
                    TipoUsuario tipoUsuario = (TipoUsuario) spTipoUsuario.getSelectedItem();
                    idTipoUsuario = tipoUsuario.getId();
                }

                IUsuarioLogic iUsuarioLogic = new UsuarioLogic();
                iUsuarioLogic.crearUsuario(txtCedula.getText().toString(),
                                            txtNombre.getText().toString(),
                                            txtApellido.getText().toString(),
                                            idTipoUsuario,
                                            this);

                Toast.makeText(this,"Registro exitosa",Toast.LENGTH_LONG).show();
                resetFields();

            }catch(Exception e){
                    notificarMensajes(e.getMessage());
            }
    }

    public void accionModificarUsuario(View view){
        try{
            Integer idTipoUsuario=null;

            if(spTipoUsuario.getSelectedItem()!=null){
                TipoUsuario tipoUsuario = (TipoUsuario) spTipoUsuario.getSelectedItem();
                idTipoUsuario = tipoUsuario.getId();
            }

            IUsuarioLogic iUsuarioLogic = new UsuarioLogic();
            iUsuarioLogic.modificarUsuario(txtCedula.getText().toString(),
                    txtNombre.getText().toString(),
                    txtApellido.getText().toString(),
                    idTipoUsuario,
                    this);

            Toast.makeText(this,"Actualizaciòn exitosa",Toast.LENGTH_LONG).show();
            resetFields();
            btnCrear.setEnabled(true);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }catch(Exception e){
            notificarMensajes(e.getMessage());
        }
    }

    public void accionEliminarUsuario(View view){
        try{

            IUsuarioLogic iUsuarioLogic = new UsuarioLogic();
            iUsuarioLogic.eliminarUsuario(txtCedula.getText().toString(),
                    this);

            Toast.makeText(this,"Se eliminò el usuario correctamente",Toast.LENGTH_LONG).show();
            resetFields();
            btnCrear.setEnabled(true);
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }catch(Exception e){
            notificarMensajes(e.getMessage());
        }
    }


    public void notificarMensajes(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    public void consultarTipoUsuarios(){
        ITipoUsuarioLogic iTipoUsuarioLogic = new TipoUsuarioLogic();
        iTipoUsuarioLogic.consultarTodosTipoUsuario(this);
    }

    public void cargarTipoUsuarios(List<TipoUsuario> ltipoUsuarios){

        if(ltipoUsuarios!=null && !ltipoUsuarios.isEmpty()){
            adapterTipoUsuario = new ArrayAdapter<TipoUsuario>(this, android.R.layout.simple_spinner_item,ltipoUsuarios);
            adapterTipoUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spTipoUsuario.setAdapter(adapterTipoUsuario);
        }
    }

    public void deshabilitarTodosBotones(){
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    public void deshablitarCamposDeEntrada(){
        txtApellido.setEnabled(false);
        spTipoUsuario.setEnabled(false);
    }


    @Override
    public void actualizarVista(DataBaseDTO dataBaseDTO) {
        Usuario usuario= dataBaseDTO.getEntidad()!=null ?((Usuario)dataBaseDTO.getEntidad()):null;

        if(dataBaseDTO.getlEntidades()!=null){
            cargarTipoUsuarios(dataBaseDTO.getlEntidades());
        }

        if(dataBaseDTO.getOperacion().equalsIgnoreCase("findId") &&
                dataBaseDTO.getEntidad()!=null){
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            seleccionarValorSpinner(usuario.getIdTipoUsuario());
            txtCedula.setEnabled(false);
            txtApellido.setEnabled(true);
            spTipoUsuario.setEnabled(true);
            btnModificar.setEnabled(true);
            btnCrear.setEnabled(false);
        }
    }

    @Override
    public void notificar(DataBaseDTO dataBaseDTO) {

    }
    public void seleccionarValorSpinner(Integer idTipoUsuario){
        for (int i=0; i< spTipoUsuario.getCount(); i ++){

            if(((TipoUsuario) spTipoUsuario.getItemAtPosition(i)).getId(). equals(idTipoUsuario)){
                spTipoUsuario.setSelection(i);
                break;
            }
        }
    }

    public void limpiarcampos(View view){
        resetFields();
    }

    public void resetFields(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setEnabled(true);
        txtApellido.setEnabled(true);
        spTipoUsuario.setEnabled(true);

        btnCrear.setEnabled(false);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
}
