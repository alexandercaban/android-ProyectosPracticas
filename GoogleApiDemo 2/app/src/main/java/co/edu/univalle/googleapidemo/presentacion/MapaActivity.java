package co.edu.univalle.googleapidemo.presentacion;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

import co.edu.univalle.googleapidemo.R;
import co.edu.univalle.googleapidemo.logica.GoogleMapsLogic;
import co.edu.univalle.googleapidemo.logica.iGoogleMapsLogic;
import co.edu.univalle.googleapidemo.modelo.dto.RutaDTO;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, PlaceSelectionListener {
    private GoogleMap mapa;
    private boolean isAuthorized = true;
    private LocationManager locationManager;
    private Double inicialLatitude;
    private Double inicialLongitude;
    private static final int CODIGO_PERMISOS_LOCATION = 1;
    private PolylineOptions polylineOptions;
    private LatLng posicionInicial;
    private LatLng posicionFinal;
    private AutocompleteSupportFragment autocompleteSupportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa);

        supportMapFragment.getMapAsync(this);

        autocompleteSupportFragment =  (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        obtenerUbicacionActual();
        inicializarBarraBusqueda();
    }


    public void inicializarBarraBusqueda(){
        Places.initialize(getApplicationContext(), AppContext.getContext().getString(R.string.googlemapskey));
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME, Place.Field.ID));
        autocompleteSupportFragment.setOnPlaceSelectedListener(this);

    }

    public void obtenerUbicacionActual(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                isAuthorized = !isAuthorized;
                //Toast.makeText(this,"Habilitar permiso de ubicacion",Toast.LENGTH_LONG).show();
                //este llama a onRequestPermissionsResult para validar si se dio o no el permiso :D
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        CODIGO_PERMISOS_LOCATION);
            }
        }

        if (isAuthorized){
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0,this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.getUiSettings().setZoomControlsEnabled(true);


        //marcador final para armar la ruta
        posicionFinal = new LatLng(3.4590,-76.5290);
        MarkerOptions marcadorFinal = new MarkerOptions();
        marcadorFinal.title("Torre de Cali");
        marcadorFinal.position(posicionFinal);
        mapa.addMarker(marcadorFinal);

    }

    public void accionRuta(View view){
        polylineOptions = new PolylineOptions();
        polylineOptions.width(3);
        polylineOptions.color(Color.RED);
        polylineOptions.add(posicionInicial);
        polylineOptions.add(posicionFinal);
        mapa.addPolyline(polylineOptions);
    }

    public void accionSatelite(View view){
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void accionHibrido(View view){
        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    public void accionTerreno(View view){
        mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    public void accionNormal(View view){
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void accionRuta2(View view) throws Exception {
        //mapa.setMapType(GoogleMap.);

        iGoogleMapsLogic iGoogleMapsLogic = new GoogleMapsLogic();

        RutaDTO rutaDTO = iGoogleMapsLogic.obtenerRuta(posicionInicial, posicionFinal);


        if(rutaDTO !=null && rutaDTO.getlPuntos()!=null && rutaDTO.getlPuntos().size()>0){


            PolylineOptions polylineOptions = new PolylineOptions();
            polylineOptions.width(2);
            polylineOptions.color(Color.GREEN);

            List<LatLng> puntoPintar = rutaDTO.getlPuntos();

            for(int i= 0; i <puntoPintar.size(); i++){
                polylineOptions.add(puntoPintar.get(i));

            }

            mapa.addPolyline(polylineOptions);
        }

        try {

        }catch (Exception e){
            Log.e("Error", "Error pintando ruta"+ e.getMessage());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        inicialLatitude = location.getLatitude();
        inicialLongitude = location.getLongitude();

        posicionInicial = new LatLng(inicialLatitude,inicialLongitude);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicionInicial,13));

        MarkerOptions marcadorInicial = new MarkerOptions();
        marcadorInicial.title("Universidad del Valle");
        marcadorInicial.position(posicionInicial);
        marcadorInicial.icon(BitmapDescriptorFactory.fromResource(R.drawable.bug));
        mapa.addMarker(marcadorInicial);

        CameraPosition cameraPosition = CameraPosition.builder().target(posicionInicial).zoom(15).bearing(90).build();

        mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),5000,null);

        Log.i("Info","Posicion inicial: "+inicialLatitude+" ---- "+inicialLongitude);

        //consultar solo una vez la ubicaciòn incial, para que no consuma a cada rato recursos cuando recargue
        //locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISOS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permiso de ubicacion concedido",Toast.LENGTH_LONG).show();
                    isAuthorized = true;
                    obtenerUbicacionActual();
                } else {
                    Toast.makeText(this,"Permiso de ubicacion no fue concedido",Toast.LENGTH_LONG).show();
                }
                break;
            // Aquí más casos dependiendo de los permisos
            // case OTRO_CODIGO_DE_PERMISOS...
        }
    }


    @Override
    public void onPlaceSelected(@NonNull Place place) {
        if(place.getLatLng()!= null){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(place.getName());
            markerOptions.position(place.getLatLng());
            mapa.addMarker(markerOptions);
            CameraPosition cameraPosition = CameraPosition.builder().target(place.getLatLng()).zoom(13).bearing(90).build();
            mapa.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);




        }
    }

    @Override
    public void onError(@NonNull Status status) {

    }
}
