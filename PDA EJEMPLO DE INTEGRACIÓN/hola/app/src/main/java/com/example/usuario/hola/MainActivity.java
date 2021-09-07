package com.example.usuario.hola;

import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.pda3505.printer.PrinterClassSerialPort;
import android.graphics.Bitmap;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Button BtnHola;
    public ImageView iv;
    private Bitmap btMap = null;
    public static PrinterClassSerialPort printerClass=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnHola = (Button) findViewById(R.id.btnhola);
        iv = (ImageView) findViewById(R.id.iv);
        printerClass = new PrinterClassSerialPort(new Handler());
        printerClass.open(this);

        BtnHola.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //btMap = Bitmap.createBitmap(100, 100, Config.ARGB_8888);
        //Canvas canvas = new Canvas(btMap);
        //canvas.drawColor(Color.BLACK);
        //iv.setImageBitmap(btMap);

        //iv.buildDrawingCache();
        //btMap = iv.getDrawingCache();


        String sbImpresion = "";
        sbImpresion += "MANUFACTURER: "+Build.MANUFACTURER +"\n";
        sbImpresion += "ID: "+Build.ID +"\n";
        sbImpresion += "HARDWARE: "+Build.HARDWARE  +"\n";
        sbImpresion += "MODEL: "+Build.MODEL +"\n";
        printerClass.printText(sbImpresion);



        //btMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.header_print);
        //printerClass.printImage(btMap);

    }

}
