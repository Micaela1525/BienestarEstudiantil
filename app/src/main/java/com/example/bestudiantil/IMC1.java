package com.example.bestudiantil;

// Importamos las bibliotecas necesarias.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class IMC1 extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc1);

        tv1= (TextView)findViewById(R.id.Peso1);
        tv2= (TextView)findViewById(R.id.Estatura12);
        tv3= (TextView)findViewById(R.id.Edad1);
        tv4=(TextView)findViewById(R.id.Genero1) ;
        tv5= (TextView)findViewById(R.id.Estado1);

        String Peso= getIntent().getStringExtra("Peso");
        tv1.setText(""+Peso);
        String Estatura= getIntent().getStringExtra("Estatura");
        tv2.setText(""+Estatura);
        String Edad= getIntent().getStringExtra("Edad");
        tv3.setText(""+Edad);

        String generomf=getIntent().getStringExtra("Genero");
        tv4.setText(""+generomf);

    }
    public void calculaimc(View view){
// Obtenemos los datos del formulario.
double peso = Double.parseDouble(tv1.getText().toString());
double altura = Double.parseDouble(tv2.getText().toString());
// Calculamos el IMC.
double IMC = peso / (altura * altura);

// Mostramos el resultado en el formulario.
DecimalFormat df = new DecimalFormat("#.##");
tv5.setText(df.format(IMC));

        // IMC por grados
// Si el IMC es mayor a 18 y menor a 24 mi peso es saludable
        if (IMC >= 18.5 && IMC <24.9) {
            // Se mostrar la respuesta en tv5  el nivel y el grado
            tv5.setText("Peso saludable\n " + IMC);
        } else if (IMC >= 25 && IMC <29.9) {
            tv5.setText("Sobrepeso\n " + IMC);
        }
        else if (IMC >= 30 && IMC <34.9) {
            tv5.setText("Obesidad moderada " + IMC);
        }
        else if (IMC >= 35 && IMC <39.9) {
            tv5.setText("Obesidad severa\n  " + IMC);
        }
        else if (IMC <= 40){
            tv5.setText("Obesidad muy severa (Obesidad mórbida)\n "+IMC);
        }
        else if (IMC >= 16 && IMC <18.4) {
            tv5.setText("Delgadez \n " + IMC);
        }
        else if (IMC >=15 && IMC <15.9) {
            tv5.setText("Delgadez Leve \n " + IMC);
        }
        else if (IMC >= 15){
            tv5.setText("Delgadez Severa\n "+IMC);
        }

    }
    public void atras(View view){
        Intent intent=new Intent(this, Datos.class);
        startActivity(intent);
        /*
        Intent intent=new Intent(this, Busqueda.class);
        startActivity(intent);
*/
    }
}