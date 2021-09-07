package chatservidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Caban
 */
public class Servidor extends Thread{
     ServerSocket socketServidor;
     Socket asesor;
     ArrayList<Asesor> arrAsesores =  new ArrayList<Asesor>();
     
     @Override
    public void run (){
        try {
            socketServidor = new ServerSocket(8089);
            do{
                 asesor = socketServidor.accept();
                 System.out.println("SE CONECTO");
                 Asesor nuevoasesor = new Asesor(asesor);
                 nuevoasesor.start();
                 arrAsesores.add(nuevoasesor);
            }while(arrAsesores.size()<= 1);
                   
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }    
    } 
    
}
