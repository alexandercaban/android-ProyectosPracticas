package com.example.alexcaban.cine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.alexcaban.cine.Adaptadores.CarteleraAdapter;
import com.example.alexcaban.cine.DataBase.SqlDAO;
import com.example.alexcaban.cine.DataBase.SqlHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import com.example.alexcaban.cine.Modelos.CarteleraADT;
import com.example.alexcaban.cine.Modelos.CategoriaADT;
import com.example.alexcaban.cine.Modelos.DefaultItemADT;
import com.example.alexcaban.cine.Serializable.SerializarObjeto;

import static com.example.alexcaban.cine.Peliculas.interfazpeliculas;

public class Cine extends AppCompatActivity {
    FragmentTabHost tabHost;
    SQLiteDatabase dbwriter;
    SQLiteDatabase dbreader;


    public static Cine interfaz;
    public ArrayList<DefaultItemADT> arrayCategoria = new ArrayList<>();
    public ArrayList<DefaultItemADT> arrayActor  = new ArrayList<>();
    public ArrayList<CarteleraADT> arrayCartelera = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cine);
        SqlHandler objSqlHandler = new SqlHandler(getApplicationContext());
        dbwriter = objSqlHandler.getWritableDatabase();
        dbreader = objSqlHandler.getReadableDatabase();

        tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("", getResources().getDrawable(R.drawable.ic_film_reel)), Peliculas.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("", getResources().getDrawable(R.drawable.ic_more_horiz_black_18px)), Categorias.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("", getResources().getDrawable(R.drawable.ic_happy_and_sad_theater_masks)), Actores.class, null);

        consultarCategorias();
        consultarActores();
        consultarCartelera();

        SerializarObjeto objSerializable = new SerializarObjeto();
        objSerializable.crearDirectorio();
        interfaz = this;

    }


    public void  crearCategorias(View V){
        ContentValues values = new ContentValues();
        values.put(SqlDAO.Categorias.COLUMN_DESCRIPCION, Categorias.EtDescripcionCategoria.getText().toString());
        dbwriter.insert(SqlDAO.Categorias.TABLE_NAME, null, values);
        Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
        Categorias.EtDescripcionCategoria.setText("");
    }


    public void consultarCategorias() {
        Cursor c = dbreader.rawQuery("select * from " + SqlDAO.Categorias.TABLE_NAME, null);

            while (c.moveToNext()) {
                DefaultItemADT objDefaultItemADT = new DefaultItemADT(c.getInt(0), c.getString(1));
                arrayCategoria.add(objDefaultItemADT);
            }
    }

    public void  crearActores(View V){
        ContentValues values = new ContentValues();
        values.put(SqlDAO.Actores.COLUMN_IDENTIFICACION, Actores.EtIdActor.getText().toString());
        values.put(SqlDAO.Actores.COLUMN_NOMBRES, Actores.EtNombreActor.getText().toString());
        values.put(SqlDAO.Actores.COLUMN_APELLIDOS, Actores.EtApellidoActor.getText().toString());
        values.put(SqlDAO.Actores.COLUMN_EMAIL, Actores.EtEmailActor.getText().toString());
        dbwriter.insert(SqlDAO.Actores.TABLE_NAME, null, values);
        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_SHORT).show();
        Actores.EtIdActor.setText("");
        Actores.EtNombreActor.setText("");
        Actores.EtApellidoActor.setText("");
        Actores.EtEmailActor.setText("");
    }

   public void consultarActores() {
        Cursor c = dbreader.rawQuery("select * from " + SqlDAO.Actores.TABLE_NAME, null);

            while (c.moveToNext()) {
                DefaultItemADT objDefaultItemADT = new DefaultItemADT(c.getInt(0), c.getString(2));
                arrayActor.add(objDefaultItemADT);
            }
    }

    public void  crearPeliculas(View V){
        ContentValues values = new ContentValues();
        values.put(SqlDAO.Peliculas.COLUMN_TITULO, Peliculas.EtPelicula.getText().toString());
        values.put(SqlDAO.Peliculas.COLUMN_RESUMEN, Peliculas.EtResumen.getText().toString());
        values.put(SqlDAO.Peliculas.COLUMN_MINUTOS_DURACION, Peliculas.EtDuracion.getText().toString());
        values.put(SqlDAO.Peliculas.COLUMN_ANNO_ESTRENO, Peliculas.EtAnio.getText().toString());
        String sbCodCategoria = String.valueOf(arrayCategoria.get(Peliculas.SpnCategorias.getSelectedItemPosition()).getId());
        values.put(SqlDAO.Peliculas.COLUMN_COD_CATEGORIA,sbCodCategoria);
        String sbCodActor = String.valueOf(arrayActor.get(Peliculas.SpnActores.getSelectedItemPosition()).getId());
        values.put(SqlDAO.Peliculas.COLUMN_COD_ACTOR, sbCodActor);
        dbwriter.insert(SqlDAO.Peliculas.TABLE_NAME, null, values);
        Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
        Peliculas.EtPelicula.setText("");
        Peliculas.EtResumen.setText("");
        Peliculas.EtDuracion.setText("");
        Peliculas.EtAnio.setText("");
    }


    public void consultarCartelera(){
        Cursor c = dbreader.rawQuery("SELECT "+ " p."+SqlDAO.Peliculas.COLUMN_TITULO+
                                                ", p."+SqlDAO.Peliculas.COLUMN_RESUMEN+
                                                ", p."+SqlDAO.Peliculas.COLUMN_ANNO_ESTRENO+
                                                ", p."+SqlDAO.Peliculas.COLUMN_MINUTOS_DURACION+
                                                ", a."+SqlDAO.Actores.COLUMN_NOMBRES+
                                                ", c."+SqlDAO.Categorias.COLUMN_DESCRIPCION+
                                     " FROM " + SqlDAO.Peliculas.TABLE_NAME+" p, "
                                              + SqlDAO.Actores.TABLE_NAME+" a, "
                                              + SqlDAO.Categorias.TABLE_NAME+ " c" +
                                     " WHERE p."+ SqlDAO.Peliculas.COLUMN_COD_ACTOR +" = a."+ SqlDAO.Actores.COLUMN_CODIGO+
                                       " AND p."+ SqlDAO.Peliculas.COLUMN_COD_CATEGORIA+" = c."+ SqlDAO.Categorias.COLUMN_CODIGO, null);

        while (c.moveToNext()){
            CarteleraADT objCarteleraADT = new CarteleraADT(c.getString(0), c.getString(1),c.getInt(2), c.getInt(3),c.getString(4), c.getString(5));
            arrayCartelera.add(objCarteleraADT);
        }
    }


    public void guardarArchivo(View v) {

        String Pelicula = Peliculas.EtPelicula.getText().toString();
        String Resumen = Peliculas.EtResumen.getText().toString();
        int Annio = Integer.parseInt(Peliculas.EtAnio.getText().toString());
        int Duracion = Integer.parseInt(Peliculas.EtDuracion.getText().toString());
        String CodCategoria = String.valueOf(arrayCategoria.get(Peliculas.SpnCategorias.getSelectedItemPosition()).getId());
        String CodActor = String.valueOf(arrayActor.get(Peliculas.SpnActores.getSelectedItemPosition()).getId());

        CarteleraADT objCarteleraADT = new CarteleraADT(Pelicula, Resumen, Annio, Duracion, CodCategoria, CodActor);
        SerializarObjeto.arrayPeliculas.add(objCarteleraADT);

        Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
        Peliculas.EtPelicula.setText("");
        Peliculas.EtResumen.setText("");
        Peliculas.EtDuracion.setText("");
        Peliculas.EtAnio.setText("");

        try {
            FileOutputStream archivo = new FileOutputStream(SerializarObjeto.file);
            ObjectOutputStream manejador = new ObjectOutputStream(archivo);
            manejador.writeObject(SerializarObjeto.arrayPeliculas);

            //leemos los datos
            CarteleraAdapter objCartelera = new CarteleraAdapter(this,SerializarObjeto.arrayPeliculas);
            interfazpeliculas.lvPeliculas.setAdapter(objCartelera);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void leerdatos(View v) {
        CarteleraAdapter objCartelera = new CarteleraAdapter(this,SerializarObjeto.arrayPeliculas);
        interfazpeliculas.lvPeliculas.setAdapter(objCartelera);
    }

}
