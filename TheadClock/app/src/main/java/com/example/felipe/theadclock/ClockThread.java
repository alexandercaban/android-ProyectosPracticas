package com.example.felipe.theadclock;



public class ClockThread extends Thread {

    private MainActivity activity;

    private int militaryHour;
    private int minutes;
    private int seconds;

    private int hour;
    private String period;

    private boolean next;

    private final int SLEEP_TIME = 1000;


    public ClockThread(MainActivity activity, int militaryHour, int minutes, int seconds) {
        this.activity = activity;
        this.militaryHour = militaryHour;
        this.minutes = minutes;
        this.seconds = seconds;

        this.setHourAndPeriod(this.militaryHour);

        this.next = true;
    }

    @Override
    public void run() {

        while (this.next) {

            if (seconds > 59) {
                this.seconds = 0;
                this.minutes++;
            }

            if (this.minutes > 59) {
                this.minutes = 0;
                this.militaryHour++;
            }

            if (this.militaryHour > 23) {
                this.militaryHour = 0;
            }

            this.setHourAndPeriod(this.militaryHour);

            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.updateClockView(hour, militaryHour, minutes, seconds, period);
                }
            });

            try {
                sleep(this.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.seconds++;
        }

    }

    private void setHourAndPeriod(int militaryHour) {

        this.hour = militaryHour;

        if (this.hour > 12) {

            this.hour = this.hour - 12;
            this.period = "PM";

            return;
        }

        if (this.hour == 0) {

            this.hour = 12;
            this.period = "AM";

            return;
        }

        if (this.hour == 12) {

            this.period = "PM";

            return;
        }

        this.period = "AM";
    }

}
