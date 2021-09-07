package com.example.alexcaban.trikisocket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class Servidor extends AppCompatActivity {
    TextView puerto, ip;
    public static Servidor activityServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servidor);

        puerto = (TextView)findViewById(R.id.puerto);
        ip = (TextView)findViewById(R.id.ip);

        puerto.setText(" 8089");
        String sbIp= getIPAddress(true);
        ip.setText(" "+sbIp);

        activityServidor = this;
        guardarPreferenciaServidor();
    }

    public void atras(View V){
        Intent objIntent = new Intent(this, MainActivity.class);
        startActivity(objIntent);

    }


    public  String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;
                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }

    public void guardarPreferenciaServidor(){
        SharedPreferences prefs = getSharedPreferences(null, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("socket", "servidor");
        editor.commit();
    }
}
