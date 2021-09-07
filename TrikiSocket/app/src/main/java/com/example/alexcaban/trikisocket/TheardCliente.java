package com.example.alexcaban.trikisocket;

import android.content.Intent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static com.example.alexcaban.trikisocket.Triki.activityTriki;

/**
 * Created by Alex Caban on 23/04/2017.
 */

public class TheardCliente extends Thread {
    String ip;
    int puerto;
    Cliente activityCliente;
    public static Socket cliente;
    String jugadorO;

    public TheardCliente(String ip, int puerto, Cliente activityCliente){
        this.ip = ip;
        this.puerto = puerto;
        this.activityCliente = activityCliente;
    }

    @Override
        public void run() {
        super.run();
        try {
            cliente = new Socket(ip, puerto);
            activityCliente.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent objIntent = new Intent(activityCliente, Triki.class);
                    activityCliente.startActivity(objIntent);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


        DataInputStream CanalEntrada = null;

        try {
            while(true) {
                CanalEntrada = new DataInputStream(cliente.getInputStream());
                jugadorO = CanalEntrada.readUTF();
                trikiJugadorO();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trikiJugadorO() {
        activityTriki.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String btnA1 = "btnA1";
                String btnA2 = "btnA2";
                String btnA3 = "btnA3";
                String btnB1 = "btnB1";
                String btnB2 = "btnB2";
                String btnB3 = "btnB3";
                String btnC1 = "btnC1";
                String btnC2 = "btnC2";
                String btnC3 = "btnC3";
                if(btnA1.equals(jugadorO)){
                    activityTriki.BtnA1.setText("O");
                    activityTriki.BtnA1.setEnabled(false);
                    activityTriki.BtnA1.setBackgroundColor(0x0000FF00);
                }else  if(btnA2.equals(jugadorO)){
                    activityTriki.BtnA2.setText("O");
                    activityTriki.BtnA2.setEnabled(false);
                    activityTriki.BtnA2.setBackgroundColor(0x0000FF00);
                }else if(btnA3.equals(jugadorO)) {
                    activityTriki.BtnA3.setText("O");
                    activityTriki.BtnA3.setEnabled(false);
                    activityTriki.BtnA3.setBackgroundColor(0x0000FF00);
                }else if(btnB1.equals(jugadorO)) {
                    activityTriki.BtnB1.setText("O");
                    activityTriki.BtnB1.setEnabled(false);
                    activityTriki.BtnB1.setBackgroundColor(0x0000FF00);
                }else if(btnB2.equals(jugadorO)) {
                    activityTriki.BtnB2.setText("O");
                    activityTriki.BtnB2.setEnabled(false);
                    activityTriki.BtnB2.setBackgroundColor(0x0000FF00);
                }else if(btnB3.equals(jugadorO)) {
                    activityTriki.BtnB3.setText("O");
                    activityTriki.BtnB3.setEnabled(false);
                    activityTriki.BtnB3.setBackgroundColor(0x0000FF00);
                }else if(btnC1.equals(jugadorO)) {
                    activityTriki.BtnC1.setText("O");
                    activityTriki.BtnC1.setEnabled(false);
                    activityTriki.BtnC1.setBackgroundColor(0x0000FF00);
                }else if(btnC2.equals(jugadorO)) {
                    activityTriki.BtnC2.setText("O");
                    activityTriki.BtnC2.setEnabled(false);
                    activityTriki.BtnC2.setBackgroundColor(0x0000FF00);
                }else if(btnC3.equals(jugadorO)) {
                    activityTriki.BtnC3.setText("O");
                    activityTriki.BtnC3.setEnabled(false);
                    activityTriki.BtnC3.setBackgroundColor(0x0000FF00);
                }
            }
        });

    }
}
