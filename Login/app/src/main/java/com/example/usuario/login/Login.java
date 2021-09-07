package com.example.usuario.login;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button botonRegistrar, botonIngresar, botonLimpiar;
    EditText etUsuario, etPassword, etNombre;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botonRegistrar= (Button) findViewById(R.id.btnRegistro);
        botonIngresar= (Button) findViewById(R.id.btnIngreso);
        botonLimpiar= (Button)findViewById(R.id.btnLimpiar);
        etUsuario = (EditText)findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etNombre= (EditText)findViewById(R.id.etNombre);
        tvResultado =(TextView)findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View view) {
    switch (view.getId()){
        case R.id.btnRegistro:
            String usuario = etUsuario.getText().toString();
            String password = etPassword.getText().toString();
            String nombre = etNombre.getText().toString();

            new AsyncRegistro().execute(usuario,password, nombre);
            break;
        case R.id.btnIngreso:
            break;
        case R.id.btnLimpiar:
            break;
    }
    }

    public class AsyncRegistro extends AsyncTask<String, Void, Object> {
        @Override
        protected Object doInBackground(String... params) {
            ArrayList<usuarios> arrUsuarios = new ArrayList<>();
            usuarios objUsuarios = new usuarios();

            objUsuarios.setStUsuario(params[0]);
            objUsuarios.setStPassword(params[1]);
            objUsuarios.setStNombre(params[2]);


            boolean ok = false;
            if (arrUsuarios.size() > 0) {
                for (int i = 0; i < arrUsuarios.size(); i++) {
                    if (arrUsuarios.get(i).getStUsuario().equals(objUsuarios.getStPassword())) {
                        ok = true;
                    }
                }
                if (!ok) {
                    arrUsuarios.add(objUsuarios);
                    limpiar();
                } else {
                    tvResultado.setText("Google is your friend.", tvResultado.BufferType.EDITABLE);
                }
            } else if (arrUsuarios.size() <= 0) {
                arrUsuarios.add(objUsuarios);
                limpiar();
            }
            return null;
        }

    }
}
