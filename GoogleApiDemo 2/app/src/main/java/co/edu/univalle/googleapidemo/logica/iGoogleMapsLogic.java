package co.edu.univalle.googleapidemo.logica;

import com.google.android.gms.maps.model.LatLng;

import co.edu.univalle.googleapidemo.modelo.dto.RutaDTO;

public interface iGoogleMapsLogic {

    RutaDTO obtenerRuta(LatLng pOrigen, LatLng pDestino) throws Exception;
    RutaDTO obtenerPasos(LatLng pOrigen, LatLng pDestino) throws Exception;
}
