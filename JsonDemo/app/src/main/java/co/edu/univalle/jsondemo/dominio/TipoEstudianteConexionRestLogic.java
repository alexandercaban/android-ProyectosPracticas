package co.edu.univalle.jsondemo.dominio;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TipoEstudianteConexionRestLogic extends AsyncTask<String,Integer,String> {

    @Override
    protected String doInBackground(String... parametros) {

        StringBuffer respuestaServicioRest = new StringBuffer();
        HttpURLConnection httpURLConnection=null;

        try{
            //Paso1.  crear una instancia de url basados en la url definida por el servicio
            URL url = new URL(parametros[0]);
            //Paso2. Abrir la conexion a la url
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //Paso3. Se define el tipo de peticion a realizar
            httpURLConnection.setRequestMethod("GET");
            //Paso4. Se debe leer el flujo obtenido como resultado de consumir el servicio rest.
            BufferedReader bufferedReader= new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));

            String lineaLeida;

            while((lineaLeida=bufferedReader.readLine())!=null){
                    respuestaServicioRest.append(lineaLeida);
            }
            bufferedReader.close();


        }catch(Exception exception){
            Log.e("ERROR","Error consltando los tipos de estudiantes");
        }finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();}
        }


        return respuestaServicioRest.toString();

    }
}
