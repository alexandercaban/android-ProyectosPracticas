package com.example.alexcaban.trikisocket;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.io.IOException;

import static com.example.alexcaban.trikisocket.TheardCliente.cliente;

public class Triki extends AppCompatActivity {
    public static Triki activityTriki;
    Button BtnA1,BtnA2, BtnA3, BtnB1, BtnB2, BtnB3, BtnC1, BtnC2, BtnC3;
    SharedPreferences prefs;
    String socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triki);
        activityTriki= this;
        BtnA1 = (Button)findViewById(R.id.btnA1);
        BtnA2 = (Button)findViewById(R.id.btnA2);
        BtnA3 = (Button)findViewById(R.id.btnA3);
        BtnB1 = (Button)findViewById(R.id.btnB1);
        BtnB2 = (Button)findViewById(R.id.btnB2);
        BtnB3 = (Button)findViewById(R.id.btnB3);
        BtnC1 = (Button)findViewById(R.id.btnC1);
        BtnC2 = (Button)findViewById(R.id.btnC2);
        BtnC3 = (Button)findViewById(R.id.btnC3);

        prefs = activityTriki.getSharedPreferences(null, Context.MODE_PRIVATE);
        socket = prefs.getString("socket", "");
    }

    public void enviarMensaje(View v){
        int id = v.getId();
        String sbMensaje = "";
        switch (id){
            case R.id.btnA1:
                sbMensaje = "btnA1";
                if(socket.equals("servidor")){
                    BtnA1.setText("O");
                    BtnA1.setEnabled(false);
                    BtnA1.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnA1.setText("X");
                    BtnA1.setEnabled(false);
                    BtnA1.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
            case R.id.btnA2:
                sbMensaje = "btnA2";
                if(socket.equals("servidor")){
                    BtnA2.setText("O");
                    BtnA2.setEnabled(false);
                    BtnA2.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnA2.setText("X");
                    BtnA2.setEnabled(false);
                    BtnA2.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
            case R.id.btnA3:
                sbMensaje = "btnA3";
                if(socket.equals("servidor")){
                    BtnA3.setText("O");
                    BtnA3.setEnabled(false);
                    BtnA3.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnA3.setText("X");
                    BtnA3.setEnabled(false);
                    BtnA3.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
            case R.id.btnB1:
                sbMensaje = "btnB1";
                if(socket.equals("servidor")){
                    BtnB1.setText("O");
                    BtnB1.setEnabled(false);
                    BtnB1.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnB1.setText("X");
                    BtnB1.setEnabled(false);
                    BtnB1.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
            case R.id.btnB2:
                sbMensaje = "btnB2";
                if(socket.equals("servidor")){
                    BtnB2.setText("O");
                    BtnB2.setEnabled(false);
                    BtnB2.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnB2.setText("X");
                    BtnB2.setEnabled(false);
                    BtnB2.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);;
                break;
            case R.id.btnB3:
                sbMensaje = "btnB3";
                if(socket.equals("servidor")){
                    BtnB3.setText("O");
                    BtnB3.setEnabled(false);
                    BtnB3.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnB3.setText("X");
                    BtnB3.setEnabled(false);
                    BtnA3.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
            case R.id.btnC1:
                sbMensaje = "btnC1";
                if(socket.equals("servidor")){
                    BtnC1.setText("O");
                    BtnC1.setEnabled(false);
                    BtnC1.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnC1.setText("X");
                    BtnC1.setEnabled(false);
                    BtnC1.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
            case R.id.btnC2:
                sbMensaje = "btnC2";
                if(socket.equals("servidor")){
                    BtnC2.setText("O");
                    BtnC2.setEnabled(false);
                    BtnC2.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnC2.setText("X");
                    BtnC2.setEnabled(false);
                    BtnC2.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
            case R.id.btnC3:
                sbMensaje = "btnC3";
                if(socket.equals("servidor")){
                    BtnC3.setText("O");
                    BtnC3.setEnabled(false);
                    BtnC3.setBackgroundColor(0x0000FF00);
                } else if(socket.equals("cliente")) {
                    BtnC3.setText("X");
                    BtnC3.setEnabled(false);
                    BtnC3.setBackgroundColor(0x0000FF00);
                }
                validarSocket(sbMensaje);
                break;
        }
    }

    public void validarSocket(String sbMensaje){

        DataOutputStream CanalSalida = null;
        if(socket.equals("servidor")){
            try {
                CanalSalida = new DataOutputStream(TheardTriki.Servidor.getOutputStream());
                CanalSalida.writeUTF(sbMensaje);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(socket.equals("cliente")){
            try {
                CanalSalida = new DataOutputStream(cliente.getOutputStream());
                CanalSalida.writeUTF(sbMensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTextButtons(int btn){
        int ArrayBotones[]= {R.id.btnA1, R.id.btnA2, R.id.btnA3, R.id.btnB1, R.id.btnB2, R.id.btnB3, R.id.btnC1, R.id.btnC2, R.id.btnC3};
        Button[] bt = new Button[ArrayBotones.length];
        /*int buttons[] = new int[9];
        buttons[0] = R.id.btnA1;
        buttons[1] = R.id.btnA2;
        buttons[2] = R.id.btnA3;
        buttons[3] = R.id.btnB1;
        buttons[4] = R.id.btnB2;
        buttons[5] = R.id.btnB3;
        buttons[6] = R.id.btnC1;
        buttons[7] = R.id.btnC2;
        buttons[8] = R.id.btnC3;*/

        for (int i=0; i<ArrayBotones.length; i++) {
            if(ArrayBotones[i]== btn){
                if(socket.equals("servidor")){
                    bt[i].setText("O");
                    bt[i].setEnabled(false);
                } else if(socket.equals("cliente")) {
                    bt[i].setText("X");
                    bt[i].setEnabled(false);
                }
            }
        }
    }
}
