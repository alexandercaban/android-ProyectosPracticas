package com.example.android.photobyintent;

import android.app.Activity;
import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Alex Caban on 9/03/2017.
 */

public class FileAdapter extends BaseAdapter{
    protected Activity objActivity;
    protected ArrayList<File> _files = new ArrayList<>();;

    public FileAdapter(Activity objActivity, ArrayList<File> _files) {
        this.objActivity = objActivity;
        this._files = _files;
    }

    @Override
    public int getCount() {
        return this._files.size();
    }

    @Override
    public Object getItem(int position) {
        return this._files.get(position);
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

        File objMenuADT = this._files.get(position);

        ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);
        //TextView descripcion =(TextView)convertView.findViewById(R.id.tvDescripcion);

        img.setImageBitmap(img.getDrawingCache());
        //descripcion.setText(""+objMenuADT.getNombre());
        img.setVisibility(View.VISIBLE);

        convertView.setTag(objMenuADT);

        return convertView;
    }
}
