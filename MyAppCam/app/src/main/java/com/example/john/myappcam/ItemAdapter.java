package com.example.john.myappcam;

/**
 * Created by John on 06/05/2017.
 */

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_imagen, parent, false);
        }

        ImageView ivItem = (ImageView) rowView.findViewById(R.id.imagen);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.fecha);

        Item item = this.items.get(position);
        tvTitle.setText(item.getFecha());
        if(item.getOpcion()==1){
            ivItem.setImageResource(item.getImage());
        }else{
            ivItem.setImageBitmap(item.getImagenCompleta());
        }



        return rowView;
    }

}