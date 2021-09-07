package com.example.alexcaban.cine.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alexcaban.cine.Modelos.CarteleraADT;
import com.example.alexcaban.cine.R;

import java.util.ArrayList;

/**
 * Created by Alex Caban on 9/03/2017.
 */

public class CarteleraAdapter extends BaseAdapter{
    protected Activity objActivity;
    protected ArrayList<CarteleraADT> arrayCarteleraADT = new ArrayList<CarteleraADT>();

    public CarteleraAdapter(Activity objActivity, ArrayList<CarteleraADT> arrayCarteleraADT) {
        this.objActivity = objActivity;
        this.arrayCarteleraADT = arrayCarteleraADT;
    }

    @Override
    public int getCount() {
        return this.arrayCarteleraADT.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arrayCarteleraADT.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.objActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.cartelera_item_list, null);
        }

        CarteleraADT objMenuADT = this.arrayCarteleraADT.get(position);

        TextView titulo = (TextView) convertView.findViewById(R.id.tvPelicula);
        TextView anio =(TextView)convertView.findViewById(R.id.tvAnio);
        TextView duracion = (TextView)convertView.findViewById(R.id.tvDuracion);
        TextView resumen = (TextView)convertView.findViewById(R.id.tvResumen);
        TextView actor =(TextView)convertView.findViewById(R.id.tvActor);
        TextView categoria = (TextView)convertView.findViewById(R.id.tvCategoria);

        titulo.setText(objMenuADT.getSbTituloPelicula());
        anio.setText(""+objMenuADT.getNuAnno());
        duracion.setText(""+objMenuADT.getNuDuracion());
        resumen.setText(objMenuADT.getSbResumen());
        actor.setText(objMenuADT.getSbActor());
        categoria.setText(objMenuADT.getSbCategoria());

        convertView.setTag(objMenuADT);

        return convertView;
    }
}
