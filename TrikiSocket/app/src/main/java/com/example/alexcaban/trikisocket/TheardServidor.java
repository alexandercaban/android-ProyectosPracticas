package com.example.alexcaban.trikisocket;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alex Caban on 23/04/2017.
 */

public class TheardServidor extends Thread {

    ServerSocket servidor;
    Socket socketServidor;

    @Override
    public void run() {
        super.run();
        try {
            servidor = new ServerSocket(8089);

            while(true){
                socketServidor = servidor.accept();
                Servidor.activityServidor.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent objIntent =  new Intent(Servidor.activityServidor, Triki.class);
                        Servidor.activityServidor.startActivity(objIntent);
                        TheardTriki objHiloLectura = new TheardTriki (socketServidor);
                        objHiloLectura.start();
                    }
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
