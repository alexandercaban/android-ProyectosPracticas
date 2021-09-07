package com.example.alexcaban.semaforo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    Button btnRojo, btnAmarillo, btnVerde;
    EditText etsegundos;
    int i;
    int count;
    int count1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRojo = (Button)findViewById(R.id.btnrojo);
        btnAmarillo = (Button)findViewById(R.id.btnamarillo);
        btnVerde = (Button)findViewById(R.id.btnverde);
        etsegundos = (EditText)findViewById(R.id.etsegundos);



    }



    public void activarsemaforo(View V){


       semaforoAsyntask objsemaforoAsyntask = new semaforoAsyntask();
        objsemaforoAsyntask.execute();


    }

    public class semaforoAsyntask extends AsyncTask<Object, String, String>{
        @Override
        protected String doInBackground(Object... params) {
                normal();
            return null;
        }

    }



    public void normal(){
        int valor = 1;
        count= 0;
        for (i = 0; i < 92; i++) {
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    etsegundos.setText(Integer.toString(i));
                    if (count <= 60) {
                        btnAmarillo.setBackgroundColor(0xff808080);
                        btnVerde.setBackgroundColor(0xff808080);
                        btnRojo.setBackgroundColor(0xffff0000);
                    } else if (count >= 60 && count <= 63) {
                        btnRojo.setBackgroundColor(0xff808080);
                        btnAmarillo.setBackgroundColor(0xffffff00);
                    } else if(count >=63){
                        btnAmarillo.setBackgroundColor(0xff808080);
                        btnVerde.setBackgroundColor(0xff00ff00);
                    }
                }

            });
            valor++;
        }

        reversa();

    }


    public void reversa(){
        int valor = 1;
        count1= 0;
        for (i = 0; i < 2; i++) {
            count1++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    etsegundos.setText(Integer.toString(i));
                    if (count1 <= 2) {
                        btnVerde.setBackgroundColor(0xff808080);
                        btnAmarillo.setBackgroundColor(0xffffff00);
                    }
                }

            });
            valor++;
        }
        semaforoAsyntask objsemaforoAsyntask = new semaforoAsyntask();
        objsemaforoAsyntask.execute();
    }


}
