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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email , password , passwort ;
    Button Regestriren , login ;
    FirebaseAuth mAuth ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email= findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwort = findViewById(R.id.password2);
        Regestriren = findViewById(R.id.Regestriren);
        login= findViewById(R.id.login);
        mAuth= FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);

            }
        });

        Regestriren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String emailid = email.getText().toString();
              String passwortid = password.getText().toString();
              String passwortidb = passwort.getText().toString();


                if (!TextUtils.isEmpty(emailid)||!TextUtils.isEmpty(passwortid)||!TextUtils.isEmpty(passwortidb)){
                    if (passwortid.equals(passwortidb)){
                        mAuth.createUserWithEmailAndPassword(emailid,passwortid).addOnCompleteListener(new OnCompleteListener <AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task <AuthResult> task) {
                                if (task.isSuccessful()){
                                    sendMain();
                                    Toast.makeText(MainActivity.this, "Sie sind mit der Email :" + email.getText()+"Regestriret", Toast.LENGTH_SHORT).show();


                                }else {
                                    String error =task.getException().getMessage();
                                    Toast.makeText(MainActivity.this, "Ferhler : "+error, Toast.LENGTH_SHORT).show();



                                }
                            }
                        });

                    }else{
                        Toast.makeText(MainActivity.this, "Das Felt Passwort und Passwort bestetigen ist noch frei ", Toast.LENGTH_SHORT).show();

                    }



                }else {
                    Toast.makeText(MainActivity.this, "Bitte fülle alle Falder Aus  ", Toast.LENGTH_SHORT).show();

                }



            }
        });




    }

    private void sendMain() {
        Intent intent = new Intent(MainActivity.this,Home.class);
        startActivity(intent);



    }

    @Override
    protected void onStart() {

        super.onStart();
    }


    private void sendMain2() {
        Intent intent = new Intent(MainActivity.this,Home.class);
        startActivity(intent);

    }
    }