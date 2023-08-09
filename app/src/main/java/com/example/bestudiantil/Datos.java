package com.example.bestudiantil;
// Importaciones

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
public class Datos extends AppCompatActivity {
//Declaracion de variable
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    //email
    private TextView tv1;
    private Button btsig;
    private Button rd;
    private Spinner spinner;
    private Spinner spinner02;
//Para poder conectar a FireBase
    FirebaseFirestore bd2=FirebaseFirestore.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
//Asignacion de Variables
        et1 = (EditText) findViewById(R.id.apellido);
        et2 = (EditText) findViewById(R.id.nombre);
        et3 = (EditText) findViewById(R.id.peso);
        et4 = (EditText) findViewById(R.id.estatura);
        et5 = (EditText) findViewById(R.id.Edad);
        //email
        tv1 = (TextView) findViewById(R.id.t11);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner02 = (Spinner) findViewById(R.id.spinner2);
        btsig = (Button) findViewById(R.id.siguiente);
        rd = (Button) findViewById(R.id.regisdatos);
        //guardar el dato
        String email= getIntent().getStringExtra("Email");
        tv1.setText(""+email);
// Aqui creo la materia con spinner
        String[] tv1 = {"", "Desarrollo de Software", "Mecanica", "Electromecanica"};
        //
        ArrayAdapter<String> adapter01 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tv1);
        spinner.setAdapter(adapter01);
// Aqui creo el genero con la ayuda del spinner
        String[] tv2 = {"", "Femenino", "Masculino"};
        //
        ArrayAdapter<String> adapter02 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tv2);
        spinner02.setAdapter(adapter02);

        rd.setOnClickListener(new View.OnClickListener() {
/*//ESTRAER EL EMAIL Y LUEGO ME MUESTRA LOS DATOS ESPECIFICOS
String correoObtenido = "correo@example.com";
// Correo obtenido durante el inicio de sesión
db.collection("usuarios")
    .whereEqualTo("correo", correoObtenido)
    .get()
    .addOnSuccessListener(queryDocumentSnapshots -> {
        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
            // Obtener los campos necesarios (nombres, apellidos, edad, peso, carrera, )
            String nombre = document.getString("nombres");
            String apellido = document.getString("apellidos");
            int edad = document.getLong("edad").intValue();
            double peso = document.getDouble("peso");
            String carrera = document.getString("carrera");

            // Hacer algo con los datos obtenidos, como mostrarlos en la interfaz de usuario
        }
    })
    .addOnFailureListener(e -> {
        // Manejar el caso de error
    });
*/

            @Override
            public void onClick(View view) {
//guarda los datos
                //Crear las tablas para que se pueda almacenar en una base de datos
                String Nombres, Apellidos, Genero, Peso, Estatura, Edad, Carrera,Email;
                // de donde traigo la informacion
                Apellidos = String.valueOf(et1.getText());
                Nombres = String.valueOf(et2.getText());
                Peso = String.valueOf(et3.getText());
                Estatura = String.valueOf(et4.getText());
                Edad = String.valueOf(et5.getText());
            /*  Email = String.valueOf(tv1.getText());*/
                Carrera = String.valueOf(spinner.getSelectedItem());
                Genero = String.valueOf(spinner02.getSelectedItem());
                Map<String, Object> Datos = new HashMap<>();
                //Guardar en la base de datos
                Datos.put("Apellidos", Apellidos);
                Datos.put("Nombres", Nombres);
                Datos.put("Peso", Peso);
                Datos.put("Estatura", Estatura);
                Datos.put("Edad", Edad);
                Datos.put("Carrera", Carrera);
                Datos.put("Genero", Genero);
                /*Datos.put("Email", Email);*/
                //La funcion del boton
                bd2.collection("Datos")
                        .add(Datos)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            //Me mostrara un cuadro de mensaje que me indique reguistardo
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(Datos.this, "Registrado", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Me mostara un cuadro de dialogo que me muetre un mensaje No registrar
                                Toast.makeText(Datos.this, "No Registra", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }


    // Pasar la informacion de una pantalla a otra pantalla a IMC1
           public void siguiente (View view){
        // De la actividad de datos se ira a la actividad  IMC1
               // La informacion de Datos del estudiante como la de Peso, estatura, edad y genero
               Intent sig = new Intent(Datos.this, IMC1.class);
               startActivity(sig);
               sig.putExtra("Peso",et3.getText().toString());
               startActivity(sig);
               sig.putExtra("Estatura",et4.getText().toString());
               startActivity(sig);
               sig.putExtra("Edad",et5.getText().toString());
               startActivity(sig);
               sig.putExtra("Genero",spinner02.getSelectedItem().toString());
               startActivity(sig);
               finish();
           }

       }




