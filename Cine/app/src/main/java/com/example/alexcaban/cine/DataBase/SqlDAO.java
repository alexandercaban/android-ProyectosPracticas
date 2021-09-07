package com.example.alexcaban.cine.DataBase;

/**
 * Created by Alex Caban on 4/03/2017.
 */

public class SqlDAO {

    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    public static class Categorias{
        public static final String TABLE_NAME = "categorias";
        public static final String COLUMN_CODIGO = "codigo";
        public static final String COLUMN_DESCRIPCION = "descripcion";

        public static final String SQL_CREATE_CATEGORIAS = "CREATE TABLE " + Categorias.TABLE_NAME +
                " (" + Categorias.COLUMN_CODIGO + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP
                     + Categorias.COLUMN_DESCRIPCION + TEXT_TYPE + ")";

        public static final String SQL_DELETE_CATEGORIAS = "DROP TABLE IF EXISTS " + Categorias.TABLE_NAME;

    }

    public static class Peliculas{
        public static final String TABLE_NAME = "peliculas";
        public static final String COLUMN_CODIGO = "codigo";
        public static final String COLUMN_TITULO= "titulo";
        public static final String COLUMN_RESUMEN= "resumen";
        public static final String COLUMN_COD_CATEGORIA= "codcategoria";
        public static final String COLUMN_MINUTOS_DURACION= "minduracion";
        public static final String COLUMN_COD_ACTOR = "codactor";
        public static final String COLUMN_ANNO_ESTRENO= "anno";

        public static final String SQL_CREATE_PELICULAS = "CREATE TABLE " + Peliculas.TABLE_NAME +
                " (" + Peliculas.COLUMN_CODIGO + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP
                     + Peliculas.COLUMN_TITULO + TEXT_TYPE + COMMA_SEP
                     + Peliculas.COLUMN_RESUMEN + TEXT_TYPE + COMMA_SEP
                     + Peliculas.COLUMN_MINUTOS_DURACION + INT_TYPE+ COMMA_SEP
                     + Peliculas.COLUMN_ANNO_ESTRENO+ INT_TYPE + COMMA_SEP
                     + Peliculas.COLUMN_COD_CATEGORIA+INT_TYPE + COMMA_SEP
                     + Peliculas.COLUMN_COD_ACTOR+INT_TYPE + COMMA_SEP
                     + "FOREIGN KEY("+ Peliculas.COLUMN_COD_CATEGORIA +")"+" REFERENCES " + Categorias.TABLE_NAME + "(" + Categorias.COLUMN_CODIGO + ")" + COMMA_SEP
                     + "FOREIGN KEY("+ Peliculas.COLUMN_COD_ACTOR +")"+" REFERENCES " + Actores.TABLE_NAME + "(" + Actores.COLUMN_CODIGO + ")"
                     + ")";

        public static final String SQL_DELETE_PELICULAS = "DROP TABLE IF EXISTS " + Peliculas.TABLE_NAME;
    }

    public static class Actores{
        public static final String TABLE_NAME = "actores";
        public static final String COLUMN_CODIGO = "codigo";
        public static final String COLUMN_IDENTIFICACION = "identificacion";
        public static final String COLUMN_NOMBRES = "nombres";
        public static final String COLUMN_APELLIDOS = "apellidos";
        public static final String COLUMN_EMAIL = "email";
        public static final String SQL_CREATE_ACTORES = "CREATE TABLE "+ Actores.TABLE_NAME
                +"(" + Actores.COLUMN_CODIGO + INT_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP
                + Actores.COLUMN_IDENTIFICACION + INT_TYPE + COMMA_SEP
                + Actores.COLUMN_NOMBRES + TEXT_TYPE + COMMA_SEP
                + Actores.COLUMN_APELLIDOS + TEXT_TYPE + COMMA_SEP
                + Actores.COLUMN_EMAIL + TEXT_TYPE +")";

        public static final String SQL_DELETE_ACTORES = "DROP TABLE IF EXISTS " + Actores.TABLE_NAME;
    }

}
