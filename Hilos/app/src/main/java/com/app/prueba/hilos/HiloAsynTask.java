package com.app.prueba.hilos;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by CarlosOspina on 18/03/17.
 */

public class HiloAsynTask extends AsyncTask<Object, String, String> {
    int i;
    @Override
    protected String doInBackground(Object... params) {
        final MainActivity inter = (MainActivity) params[0];

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
        return "true";
    }

}
