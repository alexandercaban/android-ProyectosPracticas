package com.example.alexcaban.googlemapapp.DataBase;

/**
 * Created by Alex Caban on 11/06/2017.
 */

public class SQLite {

    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    public static class Ubicacion{
        public static final String TABLE_NAME = "ubicacion";
        public static final String COLUMN_CODIGO = "codigo";
        public static final String COLUMN_LONGITUD = "longitud";
        public static final String COLUMN_LATITUD = "latitud";
        public static final String COLUMN_LUGAR = "descripcion";


        public static final String SQL_CREATE_UBICACION = "CREATE TABLE " + Ubicacion.TABLE_NAME +
                " (" + Ubicacion.COLUMN_CODIGO + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP
                + Ubicacion.COLUMN_LONGITUD + TEXT_TYPE + COMMA_SEP
                + Ubicacion.COLUMN_LATITUD + TEXT_TYPE + COMMA_SEP
                + Ubicacion.COLUMN_LUGAR + TEXT_TYPE+ ")";

        public static final String SQL_DELETE_UBICACION = "DROP TABLE IF EXISTS " + Ubicacion.TABLE_NAME;

    }

}
