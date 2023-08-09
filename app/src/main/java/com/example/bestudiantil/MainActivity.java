package com.example.bestudiantil;
//Importamos
import android.annotation.SuppressLint;
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
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity {
    // Declaracionn de variables
    TextInputEditText editTextEmail, editTextPassword;
    Button signIn;
    TextView signUp;
    FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        editTextEmail=findViewById(R.id.Email);
        editTextPassword=findViewById(R.id.password);
        signIn=findViewById(R.id.sign_in);
        signUp=findViewById(R.id.sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Esto es para que de la pagina principal se vaya a la pagina secundaria (registro)
                Intent intent=new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
        //
        signIn.setOnClickListener(new View.OnClickListener() {
            //
            @Override
            public void onClick(View view) {
                String email,password;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());
            // Ingresar un email
                if (TextUtils.isEmpty(email)){
// recibo el dato y le mando a datos del estudiante
                    Intent sig = new Intent(MainActivity.this, Datos.class);
                    startActivity(sig);
                    sig.putExtra("Email", editTextEmail.getText().toString());
                    startActivity(sig);

                    // Mensaje para que ingrese un email
                    Toast.makeText(MainActivity.this,"Ingrese el  Email",Toast.LENGTH_SHORT).show();
                    return;
                }


                // Ingresar una contraseña
                if (TextUtils.isEmpty(password)){
                    //Mensaje de ingreso de contraseña
                    Toast.makeText(MainActivity.this, "Ingrese la Contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
// Guardar la informacion en una  base de datos en Firebase
                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Si el usuario ya existe, me mostrara un cuadro de dialogo donde me indique Iniciacion de sesion exitosa
                                //si el usuario doctora es = email me va a a la parte de consulta, caso contrario se va autentificacion

                                if( editTextEmail.getText().toString().equals("ADMIN"))
                                {
                                    Intent intent=new Intent(MainActivity.this, Busqueda.class);
                                    startActivity(intent);
                                    finish();
                                }
                                    // Si el estudiante existe se dirige a la pantalla del calculo
                                else if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"Inicio de Sesion Exitosa",Toast.LENGTH_SHORT).show();
                                    // De la pantalla MainActivity se ira a la pantalla del IMC1
                                    //Usuario antiguo
                                    Intent intent=new Intent(MainActivity.this, IMC1.class);
                                    startActivity(intent);
                                    finish();
                                }
                                // Caso contrario
                                else{
                                    // me muestre un mensaje que me insdique ( Autentificacion fallida)
                                    Toast.makeText(MainActivity.this,"Usuario no registrado, Regístrate porfavor ",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

}


