package co.edu.univalle.jsondemo.presentacion;

import android.app.Application;

public class AppContext extends Application {

public static Application contexto;


public void onCreate(){
    super.onCreate();
    contexto = this;
}

public static Application getContext(){
    return contexto;
}

}
