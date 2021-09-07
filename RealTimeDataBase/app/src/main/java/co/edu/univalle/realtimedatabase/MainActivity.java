package co.edu.univalle.realtimedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCrear;
    private EditText etUsuario, etNombre, etCedula, etClave, etLogin;
    private List<TipoUsuarioDTO> lTipoUsuario= null;
    private Spinner spTipoUsuario;
    private String valorSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrear = (Button)findViewById(R.id.btnCrear);
        etNombre = (EditText)findViewById(R.id.nombre);
        etCedula = (EditText)findViewById(R.id.cedula);
        etUsuario = (EditText)findViewById(R.id.usuario);
        etLogin = (EditText)findViewById(R.id.login);
        etClave = (EditText)findViewById(R.id.clave);

        btnCrear.setOnClickListener(this);
        spTipoUsuario  = (Spinner)findViewById(R.id.tipo);
        cargarTipoUsuario();
    }

    public void validarDatos(){
        if(etNombre.getText().toString().equals("")){
            Toast.makeText(this, "Nombre requerido. por favor ingrese Nombre", Toast.LENGTH_SHORT).show();

        }
        if(etCedula.getText().toString().equals("")){
            Toast.makeText(this, "Cedula requerida. Por favor ingrese Cedula", Toast.LENGTH_SHORT).show();

        }
        if(etUsuario.getText().toString().equals("")){
            Toast.makeText(this, "Usuario requerida. Por favor ingrese Cedula", Toast.LENGTH_SHORT).show();

        }
        if(etClave.getText().toString().equals("")){
            Toast.makeText(this, "Clave requerida. Por favor ingrese Cedula", Toast.LENGTH_SHORT).show();

        }
        if(etLogin.getText().toString().equals("")){
            Toast.makeText(this, "Login requerida. Por favor ingrese Cedula", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnCrear:
                crearUsuario();

                break;
        }

    }

    public void crearUsuario(){
        try{
            IUsuarioLogic iUsuarioLogic = new UsuarioLogic();
            iUsuarioLogic.crearUsuario(etCedula.getText()+"",
                    etClave.getText()+"",
                    etUsuario.getText()+"",
                    etNombre.getText()+"",
                    valorSeleccionado);


        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }

    public void cargarTipoUsuario(){
        try {
            lTipoUsuario = new ArrayList<TipoUsuarioDTO>();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databasereference = firebaseDatabase.getReference("TipoUsuarios");
            ValueEventListener valueEventListener = databasereference.addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            lTipoUsuario.clear();
                            for(DataSnapshot iterado:dataSnapshot.getChildren()){
                                TipoUsuarioDTO tipoUsuarioDTO = iterado.getValue(TipoUsuarioDTO.class);
                                valorSeleccionado = spTipoUsuario.getSelectedItem().toString();
                                lTipoUsuario.add(tipoUsuarioDTO);
                            }
                            cargarSpinner();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("Error", databaseError.getMessage());

                        }
                    });

        }catch (Exception e){

        }
    }

    public void cargarSpinner(){
        ArrayAdapter<TipoUsuarioDTO> adapterTipoUsuario = new ArrayAdapter<TipoUsuarioDTO>(this,android.R.layout.simple_spinner_dropdown_item,lTipoUsuario);
        adapterTipoUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoUsuario.setAdapter(adapterTipoUsuario);
        spTipoUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valorSeleccionado = spTipoUsuario.getSelectedItem().toString();
                spTipoUsuario.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
