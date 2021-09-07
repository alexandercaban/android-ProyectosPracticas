package com.example.alexcaban.trikisocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import static com.example.alexcaban.trikisocket.Triki.activityTriki;

/**
 * Created by Alex Caban on 3/05/2017.
 */

public class TheardTriki extends Thread {
    public static Socket Servidor;
    public TheardTriki(Socket Servidor){
        this.Servidor  = Servidor;
    }
    String jugadorX;
    @Override
    public void run() {
        super.run();
        DataInputStream CanalEntrada = null;
        try {
            while(true) {
                CanalEntrada = new DataInputStream(Servidor.getInputStream());
                jugadorX = CanalEntrada.readUTF();
                trikiJugadorX();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trikiJugadorX() {
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
                if(btnA1.equals(jugadorX)){
                    activityTriki.BtnA1.setText("X");
                    activityTriki.BtnA1.setEnabled(false);
                    activityTriki.BtnA1.setBackgroundColor(0x0000FF00);
                }else  if(btnA2.equals(jugadorX)){
                    activityTriki.BtnA2.setText("X");
                    activityTriki.BtnA2.setEnabled(false);
                    activityTriki.BtnA2.setBackgroundColor(0x0000FF00);
                }else if(btnA3.equals(jugadorX)) {
                    activityTriki.BtnA3.setText("X");
                    activityTriki.BtnA3.setEnabled(false);
                    activityTriki.BtnA3.setBackgroundColor(0x0000FF00);
                }else if(btnB1.equals(jugadorX)) {
                    activityTriki.BtnB1.setText("X");
                    activityTriki.BtnB1.setEnabled(false);
                    activityTriki.BtnB1.setBackgroundColor(0x0000FF00);
                }else if(btnB2.equals(jugadorX)) {
                    activityTriki.BtnB2.setText("X");
                    activityTriki.BtnB2.setEnabled(false);
                    activityTriki.BtnB2.setBackgroundColor(0x0000FF00);
                }else if(btnB3.equals(jugadorX)) {
                    activityTriki.BtnB3.setText("X");
                    activityTriki.BtnB3.setEnabled(false);
                    activityTriki.BtnB3.setBackgroundColor(0x0000FF00);
                }else if(btnC1.equals(jugadorX)) {
                    activityTriki.BtnC1.setText("X");
                    activityTriki.BtnC1.setEnabled(false);
                    activityTriki.BtnC1.setBackgroundColor(0x0000FF00);
                }else if(btnC2.equals(jugadorX)) {
                    activityTriki.BtnC2.setText("X");
                    activityTriki.BtnC2.setEnabled(false);
                    activityTriki.BtnC2.setBackgroundColor(0x0000FF00);
                }else if(btnC3.equals(jugadorX)) {
                    activityTriki.BtnC3.setText("X");
                    activityTriki.BtnC3.setEnabled(false);
                    activityTriki.BtnC3.setBackgroundColor(0x0000FF00);
                }
            }
        });

    }

}

