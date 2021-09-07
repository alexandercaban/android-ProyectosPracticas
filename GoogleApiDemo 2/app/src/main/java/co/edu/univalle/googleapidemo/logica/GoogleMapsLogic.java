package co.edu.univalle.googleapidemo.logica;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import co.edu.univalle.googleapidemo.modelo.dto.RutaDTO;

public class GoogleMapsLogic implements iGoogleMapsLogic {
    @Override
    public RutaDTO obtenerRuta(LatLng pOrigen, LatLng pDestino) throws Exception {
        if (pOrigen == null){
            throw new Exception("El Origen es requerido para ocultar las rutas");

        }

        if (pDestino == null){
            throw new Exception("El Destino es requerido para ocultar las rutas");

        }

        String rutaConsultar = new ConexionRestLogic().execute(pOrigen, pDestino).get();

        if (rutaConsultar == null || rutaConsultar.equals("")){
            throw  new Exception("Error calculando la ruta en Google Direction");
        }

        JsonParser jsonParser = new JsonParser();
        JsonElement arbolJson = jsonParser.parse(rutaConsultar);
        List<LatLng> puntosPintar = null;
        RutaDTO rutaDTO = null;
        if(arbolJson.isJsonObject()){
            JSONObject jsonObject = new JSONObject(rutaConsultar);
            JSONArray rutas  = jsonObject.getJSONArray("routes");
            JSONObject rutasPintar = rutas.getJSONObject(0);
            JSONObject overviewPolyLines = rutasPintar.getJSONObject("overview_polyline");

            String puntosCodificados = overviewPolyLines.getString("points");
            puntosPintar = PolyUtil.decode(puntosCodificados);

            rutaDTO= new RutaDTO();
            rutaDTO.setlPuntos(puntosPintar);

        }

        return rutaDTO;
    }

    @Override
    public RutaDTO obtenerPasos(LatLng pOrigen, LatLng pDestino) throws Exception {
        String rutaConsultar = new ConexionRestLogic().execute(pOrigen, pDestino).get();

        if (rutaConsultar == null || rutaConsultar.equals("")){
            throw  new Exception("Error calculando la ruta en Google Direction");
        }

        JsonParser jsonParser = new JsonParser();
        JsonElement arbolJson = jsonParser.parse(rutaConsultar);
        List<LatLng> puntosPintar = null;
        RutaDTO rutaDTO = null;
        if(arbolJson.isJsonObject()){

            Gson gson = new Gson();


            JsonObject jsonObject = gson.fromJson(rutaConsultar, JsonElement.class).getAsJsonObject() ;

            JsonArray rutas  =  jsonObject.get("routes").getAsJsonArray();
            JsonObject rutasPintar = rutas.get(0).getAsJsonObject();

            JsonArray legs = rutasPintar.get("legs").getAsJsonArray();
            JsonArray Steps = legs.get(0).getAsJsonObject().get("steps").getAsJsonArray();






            rutaDTO= new RutaDTO();
            rutaDTO.setlPuntos(puntosPintar);

        }

        return null;
    }
}
