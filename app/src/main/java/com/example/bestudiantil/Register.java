package com.example.bestudiantil;
// Importaciones

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
//Declracion de variables
    TextInputEditText editTextEmail, editTextPassword;
    Button signUp;
    TextView signIn;
    FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    FirebaseFirestore db1 = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Asignacion de variables
        editTextEmail=findViewById(R.id.Email);
        editTextPassword=findViewById(R.id.password);
        signIn=findViewById(R.id.sign_in);
        signUp=findViewById(R.id.sign_up);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // De la pagina Registro se va a trasladar la pagina Main activity que es la principal
                Intent intent=new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            //
            public void onClick(View view) {
                String email,password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());

                Map<String,Object> Registro= new HashMap<>();
//
                Registro.put("Email",email);
                Registro.put("Contraseña",password);

                // recibo el dato y le mando a datos del estudiante
                Intent sig = new Intent(Register.this, Datos.class);
                startActivity(sig);
                sig.putExtra("Email", editTextEmail.getText().toString());
                startActivity(sig);
//
                db1.collection("Registro")
                        .add(Registro)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                // Mostrar un cuadro de mensaje donde me indique que se ha registrado
                                Toast.makeText(Register.this,"Registrado",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Mostar un cuadro de dialogo que me indique que no se a registrado
                                Toast.makeText(Register.this,"No Registra", Toast.LENGTH_SHORT).show();
                            }
                        });
                //
                if (TextUtils.isEmpty(email)){

                    // Me mostrar en un mensaje ingrese Email
                    Toast.makeText(Register.this,"Ingrese el Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    // Me mostrar un mensaje que me indique ingrese contraseña
                    Toast.makeText(Register.this, "Ingrese la Contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                 // Firebase la autentificacion
                    firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Si
                                if (task.isSuccessful()){
                                    // Me mostrara un mensaje de dialogo que me indique Registro Existosa
                                    Toast.makeText(Register.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                                    // De la actidad registro se ira a la actividad datos para usuarios nuevos
                                    Intent intent=new Intent(Register.this, Datos.class);
                                    startActivity(intent);
                                    finish();
                                }
                                //Caso contario
                                else {
                                    // Me mostrara un cuadro de dialogo donde me indique  autentificacion fallida
                                    Toast.makeText(Register.this,"Autentificacion Fallida", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}