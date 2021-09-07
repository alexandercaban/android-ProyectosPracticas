package co.edu.univalle.broadcastproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public TextView tvNotificacion;
    private IntentFilter cargandoIntentFilter;
    public myBroadcast cargandoBroadcastReceiver;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNotificacion = (TextView)findViewById(R.id.tvNotificacion);

        cargandoIntentFilter = new IntentFilter();
        cargandoIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        cargandoIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        cargandoBroadcastReceiver = new myBroadcast();
    }


    public class myBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean Cargando=(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED));
            notificarResultado(Cargando);
        }
    }

    public void notificarResultado(boolean Cargando){
        if(Cargando){
            tvNotificacion.setText("Esta cargando el celular...");
            tvNotificacion.setTextColor(Color.parseColor("#0000FF"));
        }else{
            tvNotificacion.setText("No esta cargando el celular...");
            tvNotificacion.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(cargandoBroadcastReceiver, cargandoIntentFilter);
    }

    @Override
    public  void onPause() {
        super.onPause();
        unregisterReceiver(cargandoBroadcastReceiver);
    }
}
