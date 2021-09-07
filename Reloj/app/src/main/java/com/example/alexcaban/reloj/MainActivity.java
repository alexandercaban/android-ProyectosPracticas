package com.example.alexcaban.reloj;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    Button BtnIniciar;
    EditText EtHora, EtMinutos;
    TextView TvReloj1, TvReloj2, Tvindicador;
    int nuHora;
    int nuMinutos;
    int Horauna;
    String strHora;
    String strMinutos;
    public MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnIniciar = (Button) findViewById(R.id.iniciarReloj);
        TvReloj1 = (TextView) findViewById(R.id.TvReloj1);
        TvReloj2 = (TextView) findViewById(R.id.TvReloj2);
        Tvindicador = (TextView) findViewById(R.id.Tvindicador);
        activity = this;
    }

    public void iniciarReloj(View V) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);
        EtHora = (EditText) dialogView.findViewById(R.id.edit1);
        EtMinutos = (EditText) dialogView.findViewById(R.id.edit2);
        dialogBuilder.setTitle("Tipos de Horas");
        dialogBuilder.setMessage("Ingrese una hora militar");
        dialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                nuHora = Integer.parseInt(EtHora.getText().toString());
                nuMinutos = Integer.parseInt(EtMinutos.getText().toString());

                if (nuHora <= 24 && nuMinutos <= 59) {
                    animarRelojMilitar();
                   // animarReloj();

                 /*Timer horario24 = new Timer();*
                    TimerTask task2 = new TimerTask() {
                        @Override
                        public void run() {
                            animarRelojMilitar();
                            animarReloj();
                     }
                    };

                    int tiempo;
                    if(nuMinutos == 0) {
                        tiempo = 1000;
                    }else{
                        tiempo = nuMinutos * 1000;
                    }
                    horario24.schedule(task2, 0, tiempo);*/


                } else {
                    Toast.makeText(getApplicationContext(), "La hora ingresada no es valida. Por favor digite una Hora valida", Toast.LENGTH_LONG).show();
                    EtHora.setText("");
                    EtMinutos.setText("");
                }
            }
        });
        dialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    public void animarReloj(){

        if (nuHora == 13) {
            Horauna = 1;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 14) {
            Horauna =2;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 15) {
            Horauna =3;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 16) {
            Horauna =4;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 17) {
            Horauna =5;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 18) {
            Horauna =6;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 19) {
            Horauna =7;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 20) {
            Horauna =8;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 21) {
            Horauna =9;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 22) {
            Horauna =10;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 23) {
            Horauna =11;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else if (nuHora == 24) {
            Horauna =12;
            TvReloj1.setText(Horauna + ":" + nuMinutos);
            Tvindicador.setText("PM");
        } else {
            TvReloj1.setText(nuHora + ":" + nuMinutos);
            Tvindicador.setText("AM");
        }
        int count = 0;
        int count1 = Horauna;

        for (int i = Horauna; i <= 12; i++) {
            count++;
            if(count == 1){
                strHora = Integer.toString(i);
                strMinutos = Integer.toString(nuMinutos);
            }else{
                count1++;
                strHora = Integer.toString(count1);
                strMinutos = Integer.toString(-1);
            }
            hiloReloj objhiloReloj = new hiloReloj(activity, strHora, strMinutos);
            objhiloReloj.start();
        }
    }

    public void animarRelojMilitar() {
        TvReloj2.setText(nuHora + ":" + nuMinutos);
        Timer horario24 = new Timer();
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {

                int count = 0;
                int count1 = nuHora;

                for (int i = nuHora; i <= 24; i++) {
                    count++;
                    if (count == 1) {
                        strHora = Integer.toString(i);
                        strMinutos = Integer.toString(nuMinutos);
                    } else {
                        count1++;
                        strHora = Integer.toString(count1);
                        strMinutos = Integer.toString(-1);
                    }
                    hiloRelojMilitar objhiloRelojMilitar = new hiloRelojMilitar(activity, strHora, strMinutos);
                    objhiloRelojMilitar.start();
                }

            }
        };

        int tiempo;
        if(nuMinutos == 0) {
            tiempo = 1000;
        }else{
            tiempo = nuMinutos * 1000;
        }
        horario24.schedule(task2, 0, tiempo);




    }

}






