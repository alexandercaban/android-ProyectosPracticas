package com.example.alexcaban.cine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import com.example.alexcaban.cine.Adaptadores.CarteleraAdapter;
import com.example.alexcaban.cine.Adaptadores.DefaultItemAdapter;
import com.example.alexcaban.cine.Modelos.CarteleraADT;
import com.example.alexcaban.cine.Serializable.SerializarObjeto;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import android.widget.AdapterView.OnItemClickListener;


public class Peliculas extends Fragment implements OnItemClickListener {

    public static Spinner SpnCategorias, SpnActores;
    public static  EditText EtPelicula, EtResumen, EtDuracion, EtAnio;
    public CarteleraADT peliculaSelecciona;
    public ListView lvPeliculas;
    public static Peliculas interfazpeliculas;

    public Peliculas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View V =  inflater.inflate(R.layout.fragment_peliculas, container, false);
        EtPelicula = (EditText)V.findViewById(R.id.EtTituloPelicula);
        EtResumen= (EditText)V.findViewById(R.id.EtResumenPelicula);
        EtDuracion=(EditText)V.findViewById(R.id.EtDuracionPelicula);
        EtAnio =(EditText)V.findViewById(R.id.EtAnioEstreno);
        SpnCategorias = (Spinner)V.findViewById(R.id.SpnCategorias);
        SpnActores = (Spinner)V.findViewById(R.id.SpnActores);
        lvPeliculas = (ListView)V.findViewById(R.id.lvListadoPeliculas);

        SpnCategorias.setAdapter(new DefaultItemAdapter(Cine.interfaz,Cine.interfaz.arrayCategoria));
        SpnActores.setAdapter(new DefaultItemAdapter(Cine.interfaz,Cine.interfaz.arrayActor));

        TextInputLayout EtTituloPeliculaWrapper = (TextInputLayout) V.findViewById(R.id.EtTituloPeliculaWrapper);
        EtTituloPeliculaWrapper.setHint("Titulo");

        TextInputLayout EtResumenPeliculaWrapper = (TextInputLayout) V.findViewById(R.id.EtResumenPeliculaWrapper);
        EtResumenPeliculaWrapper.setHint("Resumen");

        TextInputLayout EtDuracionPeliculaWrapper = (TextInputLayout) V.findViewById(R.id.EtDuracionPeliculaWrapper);
        EtDuracionPeliculaWrapper.setHint("Duracion");

        TextInputLayout EtAnioEstrenoWrapper = (TextInputLayout) V.findViewById(R.id.EtAnioEstrenoWrapper);
        EtAnioEstrenoWrapper.setHint("Año de Estreno");

        lvPeliculas.setOnItemClickListener(this);
        Cine.interfaz.consultarCartelera();
        interfazpeliculas = this;
        return V;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(Cine.interfaz)
                .setTitle("Acciones")
                .setMessage("Desea Eliminar o Editar?")
                .setPositiveButton(R.string.eliminar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SerializarObjeto.arrayPeliculas.remove(position);
                        try {
                            FileOutputStream archivo = new FileOutputStream(SerializarObjeto.file);
                            ObjectOutputStream manejador = new ObjectOutputStream(archivo);
                            manejador.writeObject(SerializarObjeto.arrayPeliculas);
                            //leemos los datos
                            CarteleraAdapter objCartelera = new CarteleraAdapter(Cine.interfaz,SerializarObjeto.arrayPeliculas);
                            interfazpeliculas.lvPeliculas.setAdapter(objCartelera);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(R.string.editar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        peliculaSelecciona = SerializarObjeto.arrayPeliculas.get(position);
                        EtPelicula.setText(peliculaSelecciona.getSbTituloPelicula());
                        EtResumen.setText(peliculaSelecciona.getSbResumen());
                        EtDuracion.setText(Integer.toString(peliculaSelecciona.getNuDuracion()));
                        EtAnio.setText(Integer.toString(peliculaSelecciona.getNuAnno()));

                        SerializarObjeto.arrayPeliculas.remove(position);
                        try {
                            FileOutputStream archivo = new FileOutputStream(SerializarObjeto.file);
                            ObjectOutputStream manejador = new ObjectOutputStream(archivo);
                            manejador.writeObject(SerializarObjeto.arrayPeliculas);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                })
                .setIcon(R.drawable.ic_movie_clapper_open)
                .show();
    }

}
