package com.example.bestudiantil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IMC extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        tv1= (TextView)findViewById(R.id.peso);
        tv2= (TextView)findViewById(R.id.estatura);
        tv3= (TextView)findViewById(R.id.edad1);
        tv4=(TextView)findViewById(R.id.respimc) ;
        tv5= (TextView)findViewById(R.id.gmf1);

        String Peso= getIntent().getStringExtra("Peso");
        tv1.setText(""+Peso);
        String Estatura= getIntent().getStringExtra("Estatura");
        tv2.setText(""+Estatura);
        String Edad= getIntent().getStringExtra("Edad");
        tv3.setText(""+Edad);

        String generomf=getIntent().getStringExtra("Genero");
        tv5.setText(""+generomf);

        // boton si es nuevo calcular mas atras
        // si es antiguo modificar no na el atras



    }
    public void calculo (View view){

        // declaracion de variables calculo mujer

        String valor1 = tv1.getText().toString();
        String valor2 = tv2.getText().toString();


        int num1= Integer.parseInt(valor1);
        int num2 = Integer.parseInt(valor2);
        // operacion calculo
        int IMC = (num1/num2+num2);
        // IMC

        if (IMC >= 18 && IMC <24) {
            tv4.setText("Peso saludable " + IMC);
        } else if (IMC >= 25 && IMC <29) {
            tv4.setText("Sobrepeso" + IMC);
        }
        else if (IMC >= 30 && IMC <34) {
            tv4.setText("Obesidad moderada " + IMC);
        }
        else if (IMC >= 35 && IMC <39) {
            tv4.setText("Obesidad severa " + IMC);
        }
        else if (IMC <= 40){
            tv4.setText("Obesidad muy severa (Obesidad mórbida)"+IMC);
        }
        else if (IMC >= 14&& IMC <15) {
            tv4.setText("Delgadez Leve" + IMC);
        }
        else if (IMC >=16 && IMC <17) {
            tv4.setText("Obesidad severa " + IMC);
        }
        else if (IMC >= 13){
            tv4.setText("Delgadez Severa"+IMC);
        }
        //hombre
        else{
            if (IMC >= 18 && IMC <24) {
                tv4.setText("Peso saludable " + IMC);
            } else if (IMC >= 25 && IMC <29) {
                tv4.setText("Sobrepeso" + IMC);
            }
            else if (IMC >= 30 && IMC <34) {
                tv4.setText("Obesidad moderada " + IMC);
            }
            else if (IMC >= 35 && IMC <39) {
                tv4.setText("Obesidad severa " + IMC);
            }
            else if (IMC <= 40){
                tv4.setText("Obesidad muy severa (Obesidad mórbida)"+IMC);
            }
            else if (IMC >= 14&& IMC <15) {
                tv4.setText("Delgadez Leve" + IMC);
            }
            else if (IMC >=16 && IMC <17) {
                tv4.setText("Obesidad severa " + IMC);
            }
            else if (IMC >= 13){
                tv4.setText("Delgadez Severa"+IMC);
            }
        }
    }
}