package com.app.prueba.hilos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mensaje;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mensaje = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar5);

        progressBar.setMax(10);

    }

    public void iniciar(View v) {
        HIloThread hilo = new HIloThread(this);
        hilo.start();

    }

    public void iniciar2(View v) {

        HiloAsynTask hiloAsyn = new HiloAsynTask();
        hiloAsyn.execute(this);
    }

}
