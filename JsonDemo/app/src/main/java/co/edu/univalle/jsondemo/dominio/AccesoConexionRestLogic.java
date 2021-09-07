package co.edu.univalle.jsondemo.dominio;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import co.edu.univalle.jsondemo.enumeraciones.EnumUrl;

public class AccesoConexionRestLogic extends AsyncTask<String,Integer,String> {




    @Override
    protected String doInBackground(String... parametros) {
        StringBuffer respuestaServicio=new StringBuffer();

        try{
            URL url = new URL(parametros[0]);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            DataOutputStream dataOutputStream= new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes("usuario="+parametros[1]);
            dataOutputStream.writeBytes("&clave="+parametros[2]);
            dataOutputStream.writeBytes("&codigoTipoEstudiante="+parametros[3]);
            dataOutputStream.flush();
            dataOutputStream.close();

            BufferedReader bufferedReader = new BufferedReader
                    ( new InputStreamReader(httpURLConnection.getInputStream()));


            String lineaLeida;

            while((lineaLeida=bufferedReader.readLine())!=null){
                    respuestaServicio.append(lineaLeida);
            }

            bufferedReader.close();




        }catch (Exception e){
            Log.e("Error","Error accediendo al sistema"+ e. getMessage());
        }



        return respuestaServicio.toString();
    }
}
