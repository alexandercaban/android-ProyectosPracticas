package com.playtechla.bluetoothprinterdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.playtechla.bluetoothprinterdemo.demo.DemoPrinter;

/**
 * Propiedad intelectual de Play Technologies S.A.S
 * SNavia
 * Playtechla.com 2016
 * Todos los derechos reservados
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iDemoPrinter = new Intent(MainActivity.this, DemoPrinter.class);
                startActivity(iDemoPrinter);
            }
        }, 5000);
    }
}
