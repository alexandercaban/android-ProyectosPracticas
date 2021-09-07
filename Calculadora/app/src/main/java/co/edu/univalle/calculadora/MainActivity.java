package co.edu.univalle.calculadora;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNumeros, concatenar;
    Button operando, btnCero, btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis ,btnSiete, btnOcho, btnNueve, btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual;
    double numero, resultado;
    boolean estaoperando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumeros = (EditText)findViewById(R.id.ETNumeros);
        etNumeros.setInputType(InputType.TYPE_NULL);
        btnCero = (Button)findViewById(R.id.cero);
        btnUno = (Button)findViewById(R.id.uno);
        btnDos = (Button)findViewById(R.id.dos);
        btnTres = (Button)findViewById(R.id.tres);
        btnCuatro = (Button)findViewById(R.id.cuatro);
        btnCinco = (Button)findViewById(R.id.cinco);
        btnSeis = (Button)findViewById(R.id.seis);
        btnSiete = (Button)findViewById(R.id.siete);
        btnOcho = (Button)findViewById(R.id.ocho);
        btnNueve = (Button)findViewById(R.id.nueve);
        btnSumar = (Button)findViewById(R.id.sumar);
        btnRestar = (Button)findViewById(R.id.restar);
        btnMultiplicar = (Button)findViewById(R.id.multiplicar);
        btnDividir = (Button)findViewById(R.id.dividir);
        btnIgual = (Button)findViewById(R.id.igual);

        btnCero.setOnClickListener(this);
        btnUno.setOnClickListener(this);
        btnDos.setOnClickListener(this);
        btnTres.setOnClickListener(this);
        btnCuatro.setOnClickListener(this);
        btnCinco.setOnClickListener(this);
        btnSeis.setOnClickListener(this);
        btnSiete.setOnClickListener(this);
        btnOcho.setOnClickListener(this);
        btnNueve.setOnClickListener(this);

        btnSumar.setOnClickListener(this);
        btnRestar.setOnClickListener(this);
        btnMultiplicar.setOnClickListener(this);
        btnDividir.setOnClickListener(this);
        btnIgual.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cero:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"0");
                break;
            case R.id.uno:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"1");
                break;
            case R.id.dos:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"2");
                break;
            case R.id.tres:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"3");
                break;
            case R.id.cuatro:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"4");
                break;
            case R.id.cinco:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"5");
                break;
            case R.id.seis:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"6");
                break;
            case R.id.siete:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"7");
                break;
            case R.id.ocho:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"8");
                break;
            case R.id.nueve:
                if(estaoperando){
                    etNumeros.setText("");
                    estaoperando = false;
                }
                concatenar = (EditText)findViewById(R.id.ETNumeros);
                etNumeros.setText(concatenar.getText().toString()+"9");
                break;
            case R.id.sumar:
                operando = (Button)findViewById(R.id.sumar);
                operacion();
                break;
            case R.id.restar:
                operando = (Button)findViewById(R.id.restar);
                operacion();
                break;
            case R.id.multiplicar:
                operando = (Button)findViewById(R.id.multiplicar);
                operacion();
                break;
            case R.id.dividir:
                operando = (Button)findViewById(R.id.dividir);
                operacion();
                break;
            case R.id.igual:
                operacion();
                break;
        }
    }

    public void operacion(){
        switch (operando.getId()){
            case R.id.sumar:
                numero = Double.parseDouble(concatenar.getText().toString());
                resultado = resultado + numero;
                etNumeros.setText(resultado+"");
                estaoperando = true;
                break;
            case R.id.restar:
                numero = Double.parseDouble(concatenar.getText().toString());
                resultado = resultado - numero;
                etNumeros.setText(resultado+"");
                estaoperando = true;
                break;
            case R.id.multiplicar:
                numero = Double.parseDouble(concatenar.getText().toString());
                resultado = resultado * numero;
                etNumeros.setText(resultado+"");
                estaoperando = true;
                break;
            case R.id.dividir:
                numero = Double.parseDouble(concatenar.getText().toString());
                resultado = resultado / numero;
                etNumeros.setText(resultado+"");
                estaoperando = true;
                break;
        }
    }
}
