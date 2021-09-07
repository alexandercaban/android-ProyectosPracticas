package com.example.john.myappcam;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ImagenActivity extends AppCompatActivity {

    TextView titulo;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        titulo = (TextView) findViewById(R.id.titulo_imagen);
        imagen = (ImageView) findViewById(R.id.imagen_grande);

        Bundle bundle = getIntent().getExtras();
        String ruta = bundle.getString("ruta");
        String nombre = bundle.getString("nombre");
        ruta = ruta + "/" + nombre;
        System.out.println("Ruta completa: " + ruta);
        Bitmap bitmap = BitmapFactory.decodeFile(ruta);
        titulo.setText(nombre);
        imagen.setImageBitmap(bitmap);
    }
}
