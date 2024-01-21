package com.example.facify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser uAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView email = findViewById(R.id.LoginTextEmailAddress);
        TextView pass = findViewById(R.id.LoginTextPassword);
        TextView signup = findViewById(R.id.ShortSignup);


        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        mAuth = FirebaseAuth.getInstance();
        uAuth = mAuth.getCurrentUser();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button log = findViewById(R.id.LoginButton);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                String passTxt = pass.getText().toString();
                if(!emailTxt.matches(emailpattern)){
                    email.setError("Invalid email");
                }
                else if(passTxt.isEmpty() || passTxt.length() < 6)
                    pass.setError("Invalid Password");
                else{
                    mAuth.signInWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                setactivity();
                                Toast.makeText(LoginActivity.this,"login Successfull",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

        TextView forget = findViewById(R.id.ShortForget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = email.getText().toString();
                if(!emailTxt.matches(emailpattern)){
                    email.setError("Invalid email");
                }
                else{
                    mAuth.sendPasswordResetEmail(emailTxt).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"check Your email",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    private void setactivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}