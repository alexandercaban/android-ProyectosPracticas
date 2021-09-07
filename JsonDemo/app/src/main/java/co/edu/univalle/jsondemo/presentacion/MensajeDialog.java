package co.edu.univalle.jsondemo.presentacion;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class MensajeDialog extends DialogFragment {
private String mensaje;

@SuppressLint("validFragment")
public MensajeDialog (String pMensaje){
        mensaje=pMensaje;
}

public MensajeDialog(){

}

public Dialog onCreateDialog(Bundle bundle){

    AlertDialog.Builder alertDialog= new AlertDialog.Builder(getActivity());
    alertDialog.setMessage(mensaje);
    alertDialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
                getDialog().dismiss();
        }
    });

    return alertDialog.create();
}


}
