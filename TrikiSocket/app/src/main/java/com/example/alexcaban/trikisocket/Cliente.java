package com.example.alexcaban.trikisocket;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cliente extends AppCompatActivity {
    EditText ip, puerto;
    Cliente activityCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        ip = (EditText)findViewById(R.id.EtIp);
        puerto = (EditText)findViewById(R.id.EtPuerto);
        activityCliente = this;
        guardarPreferenciaCliente();
    }

    public void conectarse(View V){
       String sbIp = ip.getText().toString();
       int nuPuerto =  Integer.parseInt(puerto.getText().toString());

        TheardCliente objTheardCliente = new TheardCliente(sbIp,  nuPuerto, activityCliente);
        objTheardCliente.start();

    }

    public void guardarPreferenciaCliente(){
        SharedPreferences prefs = activityCliente.getSharedPreferences(null, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("socket", "cliente");
        editor.commit();
    }


}
