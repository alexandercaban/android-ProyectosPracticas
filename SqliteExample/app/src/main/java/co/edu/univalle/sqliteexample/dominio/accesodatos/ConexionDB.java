package co.edu.univalle.sqliteexample.dominio.accesodatos;

import android.arch.persistence.room.Room;
import co.edu.univalle.sqliteexample.presentacion.AppContext;

public class ConexionDB {

    private static AppDataBase dataBaseInstance;

    private ConexionDB(){

    }

    public static AppDataBase getInstance(){
        if(dataBaseInstance == null){
            dataBaseInstance = Room.databaseBuilder(AppContext.contexto,
                    AppDataBase.class,"AppDataBase").build();
        }
        return  dataBaseInstance;
    }

}
