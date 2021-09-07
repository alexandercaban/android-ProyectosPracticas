package com.example.alexcaban.chatcliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Alex Caban on 2/04/2017.
 */

public class Cliente extends Thread {

    MainActivity actividad;
    String sbMensajes;

    public Cliente(MainActivity actividad){
        this.actividad =actividad;

    }

    Socket socketCliente;

            public void run(){
                try {
                    System.out.printf("intentandooo");
                    socketCliente = new Socket("192.168.43.119", 8089);
                    System.out.printf("se conectooo");
                } catch (IOException e) {
                    e.printStackTrace();
                }



                try {
                    DataInputStream flujo_entrada = new DataInputStream(socketCliente.getInputStream());
                    while(true){
                        sbMensajes = flujo_entrada.readUTF();
                        System.out.println(">>>"+ sbMensajes);
                        actividad.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                actividad.mensajes.append(sbMensajes);
                            }
                        });
                    }
                } catch (Exception e) {
                    System.out.println(">>" +e);
                }
            }

            public void outputMensaje(String cadena){
                DataOutputStream flujo_salida = null;
                try {
                    flujo_salida = new DataOutputStream(socketCliente.getOutputStream());
                    flujo_salida.writeUTF(cadena);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


}
