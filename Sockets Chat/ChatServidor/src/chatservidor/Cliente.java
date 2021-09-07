package chatservidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Alex Caban
 */
public class Cliente extends Thread{
     Socket socketCliente;

            public void run(){
                try {
                    socketCliente = new Socket("localhost", 8089);
                    System.out.println("SE CONECTO");
                } catch (IOException e) {
                    e.printStackTrace();
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
