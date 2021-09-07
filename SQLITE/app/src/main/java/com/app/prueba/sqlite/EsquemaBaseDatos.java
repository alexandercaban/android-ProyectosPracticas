package com.app.prueba.sqlite;

import android.provider.BaseColumns;

/**
 * Created by CarlosOspina on 28/02/17.
 */

public class EsquemaBaseDatos {

    public static class Personas {

        public static final String SQL_CREATE_PERSONAS = "CREATE TABLE " + Personas.TABLE_NAME + " (" + Personas.COLUMN_NAME_NOMBRES + TEXT_TYPE + COMMA_SEP + Personas.COLUMN_NAME_APELLIDOS + TEXT_TYPE + " )";
        public static final String SQL_DELETE_PERSONAS = "DROP TABLE IF EXISTS " + Personas.TABLE_NAME;

        public static final String TABLE_NAME = "personas";
        public static final String COLUMN_NAME_NOMBRES = "nombres";
        public static final String COLUMN_NAME_APELLIDOS = "apellidos";

    }


    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";

}
