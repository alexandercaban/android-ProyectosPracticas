package com.example.alexcaban.googlemapapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alexcaban.googlemapapp.DataBase.SQLite;
import com.example.alexcaban.googlemapapp.DataBase.SqlHandler;
import com.example.alexcaban.googlemapapp.DataBase.ubicacion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener, AdapterView.OnItemClickListener {
    GoogleMap mMap;
    EditText lati, longi, lugar;
    ListView lista;
    SQLiteDatabase dbwriter;
    SQLiteDatabase dbreader;
    ArrayList<ubicacion>  arrayUbicacion = new ArrayList<>();
    MainActivity main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SqlHandler objSqlHandler = new SqlHandler(getApplicationContext());
        dbwriter = objSqlHandler.getWritableDatabase();
        dbreader = objSqlHandler.getReadableDatabase();


        lati = (EditText)findViewById(R.id.EtLati);
        longi = (EditText)findViewById(R.id.EtLongi);
        lugar = (EditText)findViewById(R.id.EtLugar);
        lista = (ListView) findViewById(R.id.lvLista);
        lista.setOnItemClickListener(this);
        listarUbicaciones();
        main = this;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        MarkerOptions marcadorSydeny = new MarkerOptions();
        marcadorSydeny.position(sydney);
        marcadorSydeny.title("Marker in Sydney");
        marcadorSydeny.draggable(true);
        mMap.addMarker(marcadorSydeny);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                System.out.println(marker.getPosition().latitude+" - "+marker.getPosition().longitude);
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                lati.setText(""+marker.getPosition().latitude);
                longi.setText(""+marker.getPosition().longitude);
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                System.out.println(marker.getPosition().latitude+" - "+marker.getPosition().longitude);
            }
        });

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    public void guardarUbicacion(View view){
        ContentValues values = new ContentValues();
        values.put(SQLite.Ubicacion.COLUMN_LATITUD, lati.getText().toString());
        values.put(SQLite.Ubicacion.COLUMN_LONGITUD, longi.getText().toString());
        values.put(SQLite.Ubicacion.COLUMN_LUGAR, lugar.getText().toString());
        dbwriter.insert(SQLite.Ubicacion.TABLE_NAME, null, values);
        listarUbicaciones();
    }

    public void listarUbicaciones(){
        Cursor c = dbreader.rawQuery("select * from " + SQLite.Ubicacion.TABLE_NAME, null);

        while (c.moveToNext()) {

            ubicacion objDefaultItemADT = new ubicacion(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
            arrayUbicacion.add(objDefaultItemADT);

            DefaultItemAdapter objDefaultItemAdapter = new DefaultItemAdapter(this, arrayUbicacion);
            lista.setAdapter(objDefaultItemAdapter);

        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Tipos de Horas");
        dialogBuilder.setMessage("Desea eliminar o actualizar un marcador");
        dialogBuilder.setPositiveButton("actualizar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
               arrayUbicacion.remove(position);

            }
        });
        dialogBuilder.setNegativeButton("eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
