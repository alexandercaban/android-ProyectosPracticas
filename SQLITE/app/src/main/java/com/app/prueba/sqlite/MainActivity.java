package com.app.prueba.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase dbwriter;
    SQLiteDatabase dbreader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConfiguradorDb wizard = new ConfiguradorDb(getApplicationContext());
        dbwriter = wizard.getWritableDatabase();
        dbreader = wizard.getReadableDatabase();


    }

    public void crear(View v){


        EditText nombres = (EditText) findViewById(R.id.editText);
        EditText apellidos = (EditText) findViewById(R.id.editText2);


        ContentValues values = new ContentValues();
        values.put(EsquemaBaseDatos.Personas.COLUMN_NAME_NOMBRES, nombres.getText().toString());
        values.put(EsquemaBaseDatos.Personas.COLUMN_NAME_APELLIDOS, apellidos.getText().toString());
        dbwriter.insert(EsquemaBaseDatos.Personas.TABLE_NAME, null, values);

    }



    public void consultar(View v){

        String[] projection = {
                EsquemaBaseDatos.Personas.COLUMN_NAME_NOMBRES,
                EsquemaBaseDatos.Personas.COLUMN_NAME_APELLIDOS
        };

        String sortOrder = EsquemaBaseDatos.Personas.COLUMN_NAME_NOMBRES + " DESC";

        Cursor c = dbreader.rawQuery("select * from "+EsquemaBaseDatos.Personas.TABLE_NAME,null);


        while(c.moveToNext()){
            System.out.println(c.getString(0)+" "+c.getString(1));
        }


    }
}
