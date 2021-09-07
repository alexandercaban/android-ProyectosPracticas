package com.example.alexcaban.googlemapapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alexcaban.googlemapapp.DataBase.ubicacion;

import java.util.ArrayList;

/**
 * Created by Alex Caban on 11/06/2017.
 */

public class DefaultItemAdapter extends BaseAdapter {

    public static final String TAG = "TAG.MenuAdapter";

    protected Activity objActivity;
    protected ArrayList<ubicacion> objItems;

    public DefaultItemAdapter(Activity objActivity, ArrayList<ubicacion> objItems) {
        this.objActivity = objActivity;
        this.objItems = objItems;
    }


    @Override
    public int getCount() {

        return this.objItems.size();
    }

    @Override
    public Object getItem(int position) {

        return this.objItems.get(position);
    }

    @Override
    public long getItemId(int position) {

        return this.objItems.get(position).getCodigo();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) this.objActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.default_item_list, null);
        }

        ubicacion objUbicaciones = this.objItems.get(position);

        TextView longitud = (TextView) view.findViewById(R.id.EtLongitud);
        TextView latitud = (TextView) view.findViewById(R.id.EtLatitud);
        TextView lugar = (TextView) view.findViewById(R.id.EtNombre);

        longitud.setText(objUbicaciones.getLongitud());
        latitud.setText(objUbicaciones.getLatitud());
        lugar.setText(objUbicaciones.getUbicacion());

        view.setTag(objUbicaciones);

        return view;
    }
}
