package chatservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Alex Caban
 */
public class Asesor extends Thread{
    
    Socket asesores;
    public Asesor(Socket asesor){
        this.asesores = asesor;
    }
    
    public void run(){
         try {
            DataInputStream flujo_entrada = new DataInputStream(asesores.getInputStream());
            while(true){
                String sbMensajes = flujo_entrada.readUTF();
                System.out.println(">>>"+ sbMensajes);   
                
                Servidor objServidor = new Servidor();
                    for (int i = 0; i < objServidor.arrAsesores.size() ; i++) {
                     objServidor.arrAsesores.get(i).enviarMensaje(sbMensajes);
                }
                }
        } catch (Exception e) {
            System.out.println(">>"+e);
        }
    }
    
    
    
     public void enviarMensaje(String cadena) {
        DataOutputStream canalSalida = null;
        try {
            canalSalida = new DataOutputStream(asesores.getOutputStream());
            canalSalida.writeUTF(cadena);
        } catch (Exception ex) {
         ex.printStackTrace();
        }
    }
    
    
}
    

    

