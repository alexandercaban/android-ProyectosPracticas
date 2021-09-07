package com.example.usuario.hola;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by usuario Acabanillas on 18/01/2017.
 */

public class Servicio extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
