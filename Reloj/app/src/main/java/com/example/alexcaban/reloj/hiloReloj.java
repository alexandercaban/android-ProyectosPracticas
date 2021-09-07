package com.example.alexcaban.reloj;
import android.app.Activity;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alex Caban on 30/03/2017.
 */

public class hiloReloj extends Thread {

    int i;
    MainActivity activity;
    String sbHora;
    String sbMinutos;
    int nuMinutos;

    public hiloReloj(MainActivity Activity, String Hora, String Minutos){
        this.activity = Activity;
        this.sbHora = Hora;
        this.sbMinutos = Minutos;
    }

    public void run(){
        nuMinutos = Integer.parseInt(sbMinutos);
        for (i = nuMinutos; i < 59; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.TvReloj1.setText(sbHora + ":" + i);
                }
            });
        }

    }



}
