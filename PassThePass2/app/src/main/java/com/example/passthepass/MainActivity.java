package com.example.passthepass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //Datos de la actividad del Login
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Compruebo si ya estamos conectados a la aplicación, en ese caso, pasamos a la actividad Passwords
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(context, PasswordsActivity.class));
        }

    }

    public void onClick(View view){
        String e = email.getText().toString();
        String p = password.getText().toString();
        if(e.isEmpty() || p.isEmpty()){
            Toast.makeText(context, R.string.login_field_error, Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(e, p)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // En caso de que la conexión tenga exito, mostramos un Toast y pasamos a la siguiente pantalla
                                Toast.makeText(context, R.string.login_success , Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(context, PasswordsActivity.class));
                            }
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, R.string.login_failed , Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}