package com.example.alexcaban.clock;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtHour, txtMinutes;
    TextView lblResultClock, lblResultMilitaryClock;
    Button btnIniciar;
    ClockHilo clock;
    public MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblResultClock = (TextView) findViewById(R.id.lbl_clock);
        lblResultMilitaryClock = (TextView) findViewById(R.id.lbl_clock2);
        btnIniciar = (Button) findViewById(R.id.btn_iniciar);


        activity = this;

    }


    public void iniciarReloj(View V) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        txtHour = (EditText) dialogView.findViewById(R.id.edit1);
        txtMinutes = (EditText) dialogView.findViewById(R.id.edit2);

        dialogBuilder.setTitle("Tipos de Horas");
        dialogBuilder.setMessage("Ingrese una hora militar");
        dialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                if (clock == null && validarHoras()) {
                    clock = new ClockHilo(activity,
                            Integer.parseInt(txtHour.getText().toString()),
                            Integer.parseInt(txtMinutes.getText().toString())
                    );

                    clock.start();
                    btnIniciar.setEnabled(false);
                }

            }
        });
        dialogBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                validarHoras();

            }
        });
        dialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private boolean validarHoras() {

        try {

            int hour = Integer.parseInt(txtHour.getText().toString());
            int minutes = Integer.parseInt(txtMinutes.getText().toString());

            if (hour >= 0 && hour <= 23 && minutes >= 0 && minutes <= 59 ) {
                return true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "La hora introducida no es correcta", Toast.LENGTH_LONG).show();

        return false;
    }


    public void actualizarReloj(int hour, int militaryHour, int minutes, int seconds, String period) {
        this.lblResultClock.setText(hour + ":" + minutes  + " " + period);
        this.lblResultMilitaryClock.setText(militaryHour + ":" + minutes);
    }
}
