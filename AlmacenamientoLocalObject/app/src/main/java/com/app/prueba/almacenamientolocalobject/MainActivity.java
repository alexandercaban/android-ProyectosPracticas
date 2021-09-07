package com.app.prueba.almacenamientolocalobject;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    File file;
    ArrayList<Persona> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        directorio();
    }

    public void guardar(View v) {
        EditText texto1 = (EditText) findViewById(R.id.editText);
        EditText texto2 = (EditText) findViewById(R.id.editText2);

        Persona temp = new Persona();
        temp.setNombre(texto1.getText().toString());
        temp.setApellido(texto2.getText().toString());
        lista.add(temp);
        try {
            FileOutputStream archivo = new FileOutputStream(file);
            ObjectOutputStream manejador = new ObjectOutputStream(archivo);
            manejador.writeObject(lista);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void directorio(){
        File path = Environment.getExternalStorageDirectory();
        String[] directorios = path.list();

        int bandera = 0;
        for (String archivo : directorios){
            System.out.println("Archivo : "+archivo);
            if(archivo.equals("documento.bin")){
                bandera = 1;
                break;
            }
        }

        file = new File(path, "documento.bin");

        if(bandera == 1){
            cargarDatos();
        }else{
            lista = new ArrayList<>();
        }
        System.out.println("ESTA ES LA RUTA"+path);
    }

    public void cargarDatos(){
        try {
            FileInputStream archivo = new FileInputStream(file);
            ObjectInputStream manejador = new ObjectInputStream(archivo);
            Toast.makeText(this,""+manejador, Toast.LENGTH_LONG).show();
            lista = (ArrayList<Persona>) manejador.readObject();


            DataInputStream dis = new DataInputStream(archivo);
            String string = dis.readLine(); // Hacer algo con la cadena
            Toast.makeText(this,string, Toast.LENGTH_LONG).show();

     } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void leerdatos(View v) {
        for (Persona perTem : lista) {
            System.out.println(perTem.getNombre() + " " + perTem.getApellido());
            Toast.makeText(this,perTem.getNombre() + " " + perTem.getApellido(), Toast.LENGTH_LONG).show();
        }
    }
}
