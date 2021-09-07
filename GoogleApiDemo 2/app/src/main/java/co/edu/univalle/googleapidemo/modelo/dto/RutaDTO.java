package co.edu.univalle.googleapidemo.modelo.dto;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class RutaDTO {

    private List<LatLng> lPuntos;
    private String inidcaciones;

    public List<LatLng> getlPuntos() {
        return lPuntos;
    }

    public void setlPuntos(List<LatLng> lPuntos) {
        this.lPuntos = lPuntos;
    }

    public String getInidcaciones() {
        return inidcaciones;
    }

    public void setInidcaciones(String inidcaciones) {
        this.inidcaciones = inidcaciones;
    }

}
