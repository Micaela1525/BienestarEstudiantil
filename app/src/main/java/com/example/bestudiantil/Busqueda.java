package com.example.bestudiantil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Busqueda extends AppCompatActivity {

    private Spinner spinnerEdad;
    private Spinner spinnerEstatura;
    private Spinner spinnerPeso;
    private TextView textViewResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        spinnerEdad = findViewById(R.id.spinner3);
        spinnerEstatura = findViewById(R.id.spinner4);
        spinnerPeso = findViewById(R.id.spinner5);
        textViewResult = findViewById(R.id.textViewResult);

        // Crear los ArrayAdapter para los Spinners con los datos que proporcionaste
        String[] tv1 = {"55","56","57","58","59","60", "61",
                "62", "63", "64", "65", "66", "67", "68", "69",
                "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80"};
        ArrayAdapter<String> adapter01 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tv1);
        spinnerEdad.setAdapter(adapter01);

        String[] tv2 = {"1.58", "1.59", "1.60", "1.61",
                "1.62", "1.63", "1.64", "1.65", "1.66", "1.67",
                "1.68", "1.69", "1.70", "1.71", "1.72", "1.73",
                "1.74", "1.75"};
        ArrayAdapter<String> adapter02 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tv2);
        spinnerEstatura.setAdapter(adapter02);

        String[] tv3 = {"18", "19", "20", "21",
                "22", "23", "24", "25","35"};
        ArrayAdapter<String> adapter03 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tv3);
        spinnerPeso.setAdapter(adapter03);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buscar
                String edadSeleccionadaStr = spinnerEdad.getSelectedItem().toString();
                String estaturaSeleccionadaDbl = spinnerEstatura.getSelectedItem().toString();
                String pesoSeleccionadoInt = spinnerPeso.getSelectedItem().toString();

                // Mensajes de depuración para verificar los valores seleccionados
                Log.d("Depuración", "Edad seleccionada: " + edadSeleccionadaStr);
                Log.d("Depuración", "Estatura seleccionada: " + estaturaSeleccionadaDbl);
                Log.d("Depuración", "Peso seleccionado: " + pesoSeleccionadoInt);
// Buscando
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Query query = db.collection("Datos")
                        .whereEqualTo("Edad", edadSeleccionadaStr)
                        .whereEqualTo("Estatura", estaturaSeleccionadaDbl)
                        .whereEqualTo("Peso", pesoSeleccionadoInt);
//Preguntar al Inge
                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        List<String> names = new ArrayList<>();
                        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                            String Nombres = document.getString("Nombres");
                            String Apellidos = document.getString("Apellidos");
                            names.add(Nombres + " " + Apellidos);
                        }
                        if (names.isEmpty()) {
                            textViewResult.setText("No se encontraron resultados.");
                        } else {
                            StringBuilder resultText = new StringBuilder();
                            for (String name : names) {
                                resultText.append(name).append("\n");
                            }
                            textViewResult.setText(resultText.toString());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNnoemiull Exception e) {
                        textViewResult.setText("Error al obtener los datos: " + e.getMessage());
                    }
                });
            }
        });
    }
}