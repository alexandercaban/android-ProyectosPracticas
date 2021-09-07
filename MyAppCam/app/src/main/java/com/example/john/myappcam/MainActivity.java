package com.example.john.myappcam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    ArrayList<Item> items = new ArrayList();
    static String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myAppCam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);

        File f = new File(file_path);
        File[] files = f.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            File file = files[i];
            if (file.isDirectory() == false) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                items.add(new Item(bitmap, file.getName()));
            }
        }

        this.listView.setAdapter(new ItemAdapter(this, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapter, View view, int position, long arg) {
            Item item = (Item) listView.getAdapter().getItem(position);
                Intent intent = new Intent (view.getContext(), ImagenActivity.class);
                intent.putExtra("ruta", file_path);
                intent.putExtra("nombre", item.getFecha());
                startActivityForResult(intent, 0);

            }
        });

    }

    public  void abrirCamara(View v){
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            } catch (Exception ex) {
                System.out.println("Error 1");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            File imagen = null;
            try {
                imagen = createImageFile();
                FileOutputStream fOut = new FileOutputStream(imagen);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                fOut.flush();
                fOut.close();
                items.add(new Item(imageBitmap, imagen.getName()));
                this.listView.setAdapter(new ItemAdapter(this, items));
                System.out.println("SEEEE GUARDOOOO LA IIIMMAAAAGENNNN");
            }
            catch(Exception e) {
                System.out.println("NO GUARDOOOOOOOOOO");
            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        String CurrentDateAndTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File image = File.createTempFile(CurrentDateAndTime,".jpg",dir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
