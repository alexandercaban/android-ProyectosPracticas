package com.example.alexcaban.trikisocket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Servidor activityServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarCliente(View V){
        Intent objIntent = new Intent(this, Cliente.class);
        startActivity(objIntent);
    }

    public void iniciarServidor(View V){
        TheardServidor objTheadServidor = new TheardServidor();
        objTheadServidor.start();
        Intent objIntent = new Intent(this, Servidor.class);
        startActivity(objIntent);
    }
}
