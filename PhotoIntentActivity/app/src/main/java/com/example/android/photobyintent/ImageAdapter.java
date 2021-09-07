package com.example.android.photobyintent;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex Caban on 9/03/2017.
 */

public class ImageAdapter extends BaseAdapter{
    protected Activity objActivity;
    protected ArrayList<image> arrayImageADT = new ArrayList<image>();

    public ImageAdapter(Activity objActivity, ArrayList<image> arrayImageADT) {
        this.objActivity = objActivity;
        this.arrayImageADT = arrayImageADT;
    }

    @Override
    public int getCount() {
        return this.arrayImageADT.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arrayImageADT.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.objActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.photos_item_list, null);
        }

        image objMenuADT = this.arrayImageADT.get(position);

        ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);


        img.setImageBitmap(objMenuADT.getImagenes());
        img.setVisibility(View.VISIBLE);

        convertView.setTag(objMenuADT);

        return convertView;
    }
}
