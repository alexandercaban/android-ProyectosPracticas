package com.app.prueba.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosOspina on 28/02/17.
 */

public class ConfiguradorDb extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BdPruebas.db";

    public ConfiguradorDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EsquemaBaseDatos.Personas.SQL_CREATE_PERSONAS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EsquemaBaseDatos.Personas.SQL_DELETE_PERSONAS);
        onCreate(db);
    }

}
