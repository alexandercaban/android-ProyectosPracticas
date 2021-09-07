package co.edu.univalle.animacionexample.presentacion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthinkcg.dependenciademo.utilidades.Utilidades;

import co.edu.univalle.animacionexample.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btn1, btn2, btn3, btn4, btn5;
    public TextView tv1,tv2,tv3;
    public ImageView imagen;
    public Float Delta;
    public int valorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inicioAplicacion();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1= findViewById(R.id.boton1);
        btn2= findViewById(R.id.boton2);
        btn3= findViewById(R.id.boton3);
        btn4= findViewById(R.id.boton4);
        btn5= findViewById(R.id.boton5);
        imagen = findViewById(R.id.cohete);


        tv1= findViewById(R.id.textoAnimado1);
        tv2 = findViewById(R.id.textoAnimado2);
        tv3 = findViewById(R.id.textoAnimado3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        Delta = 1F;


    }

    public void inicioAplicacion(){
        try {

            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(   1000);
            setTheme(R.style.AppTheme_Launcher);
            Thread.sleep(1000);
            Utilidades  objUtilidades = new Utilidades();

            Log.i("Info", objUtilidades.saludando("Alexander Cabanillas"));
        }catch (Exception e){

        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.boton1):
                ObjectAnimator objAnimator = ObjectAnimator.ofFloat(tv1, "rotation", 180);
                objAnimator.setDuration(3000);
                objAnimator.start();
                break;
            case (R.id.boton2):
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,6);
                valueAnimator.setDuration(4000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Double valorCalculado = Double.parseDouble(animation.getAnimatedValue().toString());
                        tv2.setText(valorCalculado.toString());
                    }
                });
                valueAnimator.start();
                break;
            case (R.id.boton3):
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv3, "translationX", 100F);
                objectAnimator.setDuration(6000);
                objectAnimator.start();
                    break;
            case (R.id.boton4):
                ValueAnimator valueAnimator1 = new ValueAnimator();
                valueAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator1.setDuration(3000);
                valueAnimator1.setEvaluator(new FloatEvaluator());
                valueAnimator1.setFloatValues(imagen.getY(), imagen.getY()+500);
                valueAnimator1.setRepeatMode(ValueAnimator.REVERSE);
                valueAnimator1.setRepeatCount(3);

                ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(0,6);
                valueAnimator2.setDuration(4000);
                valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Double valorCalculado = Double.parseDouble(animation.getAnimatedValue().toString());
                        tv2.setText(valorCalculado.toString());
                    }
                });


                ObjectAnimator objAnimator1 = ObjectAnimator.ofFloat(imagen, "rotation", 180);
                objAnimator1.setDuration(3000);
                objAnimator1.start();

                valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        imagen.setY((Float) animation.getAnimatedValue());
                    }
                });

                valueAnimator1.start();

                break;
            case (R.id.boton5):
                    animar();
                break;
        }
    }


    public void animar(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);


        if(Delta == 1){
            valorFinal = point.y-450;
        }else{
            valorFinal = 400;
        }

        ValueAnimator valueAnimator3 = ValueAnimator.ofFloat(imagen.getY(), valorFinal);
        valueAnimator3.setDuration(700);

        valueAnimator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                btn5.setClickable(false);
                Log.i("Info", "iniciando la animaciòn" );
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btn5.setClickable(true);
                Log.i("Info", "finalizando la animaciòn" );
                Delta *= -1;

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(rotar90negativo()).after(avanzarHorizontal());

                animar2();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

        });

        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i("Info", ""+ animation.getAnimatedValue() );
                imagen.setY(new Float(animation.getAnimatedValue()+""));
            }
        });

        valueAnimator3.start();
    }



    public void animar2(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        ValueAnimator valueAnimator3 = ValueAnimator.ofFloat(imagen.getY(), valorFinal);
        valueAnimator3.setDuration(700);

        valueAnimator3.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btn5.setClickable(true);
                Delta *= -1;
                Log.i("Info", "finalizando la animaciòn" );

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(rotar180()).after(avanzarVertical());

                animar3();
            }

        });
        valueAnimator3.start();
    }

    public void animar3() {
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        ValueAnimator valueAnimator3 = ValueAnimator.ofFloat(imagen.getX(), valorFinal);
        valueAnimator3.setDuration(700);

        valueAnimator3.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btn5.setClickable(true);
                Delta *= -1;
                Log.i("Info", "finalizando la animaciòn");

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(rotar90positivo()).after(avanzarVertical2());
            }

        });
        valueAnimator3.start();
    }

    public ObjectAnimator avanzarHorizontal(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imagen, "translationX", 900F);
        objectAnimator.setDuration(700);
        objectAnimator.start();
        return objectAnimator;
    }

    public ObjectAnimator rotar90negativo(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imagen, "rotation", -90);
        objectAnimator.start();
        return objectAnimator;
    }

    public ObjectAnimator avanzarVertical(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imagen, "translationY", 90F);
        objectAnimator.setDuration(700);
        objectAnimator.start();
        return objectAnimator;
    }

    public ObjectAnimator rotar180(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imagen, "rotation", -180);
        objectAnimator.start();
        return objectAnimator;
    }

    public ObjectAnimator avanzarVertical2(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imagen, "translationX", -5F);
        objectAnimator.setDuration(700);
        objectAnimator.start();
        return objectAnimator;
    }

    public ObjectAnimator rotar90positivo(){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imagen, "rotation", 90);
        objectAnimator.start();
        return objectAnimator;
    }




}
