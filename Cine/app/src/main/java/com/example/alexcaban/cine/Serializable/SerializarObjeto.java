package com.example.alexcaban.cine.Serializable;

import android.os.Environment;
import com.example.alexcaban.cine.Modelos.CarteleraADT;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by Alex Caban on 14/03/2017.
 */

public class SerializarObjeto  {
    public static ArrayList<CarteleraADT> arrayPeliculas;
    public static File file;

    public void crearDirectorio(){
        File path = Environment.getExternalStorageDirectory();
        String[] directorios = path.list();

        int bandera = 0;
        for (String archivo : directorios){
            System.out.println("Archivo : "+archivo);
            if(archivo.equals("peliculas.bin")){
                bandera = 1;
                break;
            }
        }

        file = new File(path, "peliculas.bin");

        if(bandera == 1){
            cargarDatos();
        }else{
            arrayPeliculas = new ArrayList<>();
        }
        System.out.println("ESTA ES LA RUTA"+path);
    }

    public void cargarDatos(){
        try {
            FileInputStream archivo = new FileInputStream(file);
            ObjectInputStream manejador = new ObjectInputStream(archivo);
            arrayPeliculas = (ArrayList<CarteleraADT>) manejador.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
