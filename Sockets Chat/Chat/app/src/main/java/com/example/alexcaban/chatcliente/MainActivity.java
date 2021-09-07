package com.example.alexcaban.chatcliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    EditText mensajes, area;
    ImageButton enviar;
    Cliente objCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensajes = (EditText)findViewById(R.id.etMensajes);
        area = (EditText)findViewById(R.id.etArea);
        enviar = (ImageButton)findViewById(R.id.btEnviar);

        objCliente = new Cliente(this);
        objCliente.start();
        System.out.println("pruebaaaaaaa");
    }


    public void enviarMensaje(View v){
        String msg = mensajes.getText().toString();
        objCliente.outputMensaje(msg);
    }


}
