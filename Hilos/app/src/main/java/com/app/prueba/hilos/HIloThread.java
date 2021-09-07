package com.app.prueba.hilos;

import android.app.Activity;
import android.widget.TextView;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by CarlosOspina on 18/03/17.
 */

public class HIloThread extends Thread {

    MainActivity inter;
    int i;

    public HIloThread(MainActivity inter ){
       this.inter = inter;
    }

    public void run(){
        for (i = 1; i < 11; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HIloThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Vamos en " + i);




            inter.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    inter.mensaje.setText("Vamos en " + i);
                    inter.progressBar.setProgress(i);
                }
            });

        }
    }
}

