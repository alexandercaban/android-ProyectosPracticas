package com.example.alexcaban.cine.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alexcaban.cine.DataBase.SqlDAO;

/**
 * Created by Alex Caban on 4/03/2017.
 */

public class SqlHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BdCine.db";

    public SqlHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SqlDAO.Categorias.SQL_CREATE_CATEGORIAS);
        db.execSQL(SqlDAO.Peliculas.SQL_CREATE_PELICULAS);
        db.execSQL(SqlDAO.Actores.SQL_CREATE_ACTORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SqlDAO.Categorias.SQL_DELETE_CATEGORIAS);
        db.execSQL(SqlDAO.Peliculas.SQL_DELETE_PELICULAS);
        db.execSQL(SqlDAO.Actores.SQL_DELETE_ACTORES);
        onCreate(db);

    }
}
