package com.example.felipe.theadclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtMilitaryHour, txtMinutes, txtSeconds;

    private TextView lblResultClock, lblResultMilitaryClock;

    private Button btnStartClock;

    private ClockThread clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtMilitaryHour = (EditText) findViewById(R.id.txt_military_hour);
        this.txtMinutes = (EditText) findViewById(R.id.txt_minutes);
        this.txtSeconds = (EditText) findViewById(R.id.txt_seconds);

        this.lblResultClock = (TextView) findViewById(R.id.lbl_result_clock);
        this.lblResultMilitaryClock = (TextView) findViewById(R.id.lbl_result_military_clock);

        this.btnStartClock = (Button) findViewById(R.id.btn_start_clock);
    }

    public void startClock(View v) {

        if (this.clock == null && this.validateEnteredHour()) {

            this.clock = new ClockThread(
                    this,
                    Integer.parseInt(this.txtMilitaryHour.getText().toString()),
                    Integer.parseInt(this.txtMinutes.getText().toString()),
                    Integer.parseInt(this.txtSeconds.getText().toString())
            );

            this.clock.start();

            this.btnStartClock.setEnabled(false);
        }

    }

    public void updateClockView(int hour, int militaryHour, int minutes, int seconds, String period) {

        this.lblResultClock.setText(hour + ":" + minutes + ":" + seconds + " " + period);
        this.lblResultMilitaryClock.setText(militaryHour + ":" + minutes + ":" + seconds);
    }

    private boolean validateEnteredHour() {

        try {
            int hour = Integer.parseInt(txtMilitaryHour.getText().toString());
            int minutes = Integer.parseInt(txtMinutes.getText().toString());
            int seconds = Integer.parseInt(txtSeconds.getText().toString());

            if (hour >= 0 && hour <= 23 && minutes >= 0 && minutes <= 59 && seconds >= 0 && seconds <= 59) {
                return true;
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        Toast.makeText(this, "La hora introducida no es correcta", Toast.LENGTH_LONG).show();

        return false;
    }
}
