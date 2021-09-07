package co.edu.univalle.googleapidemo.logica;

import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import co.edu.univalle.googleapidemo.R;
import co.edu.univalle.googleapidemo.modelo.enumeracion.EnumUrl;
import co.edu.univalle.googleapidemo.presentacion.AppContext;

public class ConexionRestLogic extends AsyncTask<LatLng, Long, String> {

    private static final String URL_CONSULTAR;

    static{
        URL_CONSULTAR = EnumUrl.ENUM_URL.getValor()+"?";
    }

    @Override
    protected String doInBackground(LatLng... latLngs) {

        StringBuffer respuestaRuta = null;
        try {
            StringBuffer peticion  = new StringBuffer();


            //https://maps.googleapis.com/maps/api/directions/json?
            // origin=3.3749
            // ,
            // -76.5335
            // &
            // destination=
            // 3.4372200
            // ,
            // -76.5225000
            // &
            // mode
            // =
            // driving
            // &
            // key
            // =
            // AIzaSyB3L_y-afL_ImmWCbsMYfCgigmp3x_-xgw
            peticion.append(URL_CONSULTAR);
            peticion.append("origin=");
            peticion.append(latLngs[0].latitude);
            peticion.append(",");
            peticion.append(latLngs[0].longitude);
            peticion.append("&");
            peticion.append("destination=");
            peticion.append(latLngs[1].latitude);
            peticion.append(",");
            peticion.append(latLngs[1].longitude);
            peticion.append("&");
            peticion.append("languaje=");
            peticion.append(EnumUrl.LENGUAJE_ESPANOL.getValor());
            peticion.append("&");
            peticion.append("mode=");
            peticion.append(EnumUrl.MODO_CONDUCIENDO.getValor());
            peticion.append("&");
            peticion.append("key=");
            peticion.append(AppContext.getContext().getString(R.string.googlemapskey));

            URL url = new URL (peticion.toString());
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));

            String lineaLeida;
            respuestaRuta = new StringBuffer();

            while((lineaLeida = bufferedReader.readLine())!=null){
                respuestaRuta.append(lineaLeida);
            }

            bufferedReader.close();
            httpsURLConnection.disconnect();


        }catch (Exception exception){
                Log.e("error", "error consultando ruta "+ exception.getMessage());
        }


        return respuestaRuta.toString();
    }
}
