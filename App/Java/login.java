package de.techniktv.ed.tzz.url.appstore.techniktv.techniktv.logintest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email , passwort ;
    Button login , Regestriren ;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emaillogin);
        passwort = findViewById(R.id.passwordlogin);
        login = findViewById(R.id.login);
        Regestriren = findViewById(R.id.Regestriren);
        mAuth = FirebaseAuth.getInstance();

        Regestriren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = email.getText().toString();
                String password = passwort.getText().toString();

                if (!TextUtils.isEmpty(emailid) || !TextUtils.isEmpty(password)) {

                    mAuth.signInWithEmailAndPassword(emailid,password ).addOnCompleteListener(new OnCompleteListener <AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task <AuthResult> task) {
                            if (task.isSuccessful()) {
                                sendMain();

                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(login.this, "Ferhler : " + error, Toast.LENGTH_LONG).show();


                            }
                        }
                    });

                }

            }
        });
    }

    private void sendMain() {
        Intent intent = new Intent(login.this,Home.class);
        startActivity(intent);
        Toast.makeText(this, "Sie sind ihren konto angemeldet das konto gehört zu der Email "+ email.getText(), Toast.LENGTH_LONG).show();


    }
}