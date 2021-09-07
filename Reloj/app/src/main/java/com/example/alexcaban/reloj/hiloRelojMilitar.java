package com.example.alexcaban.reloj;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alex Caban on 8/04/2017.
 */

public class hiloRelojMilitar extends Thread {


    int i;
    MainActivity activity;
    String sbHora;
    String sbMinutos;
    int nuMinutos;

    public hiloRelojMilitar(MainActivity Activity, String Hora, String Minutos){
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
                    activity.TvReloj2.setText(sbHora + ":" + i);
                }
            });
        }

    }
}
