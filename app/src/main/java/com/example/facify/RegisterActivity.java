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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser uAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView sign = findViewById(R.id.Already);
        mAuth = FirebaseAuth.getInstance();
        uAuth = mAuth.getCurrentUser();

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        final TextView email = findViewById(R.id.editTextTextEmailAddress2);
        final TextView pass = findViewById(R.id.editTextTextPassword2);
        final TextView com_pass = findViewById(R.id.editTextTextPassword3);
        final TextView name = findViewById(R.id.editTextTextPersonName);
        Button regis = findViewById(R.id.RegisterButton);
//        String emailpattern = "^[A-Za-z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        regis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String emailTxt = email.getText().toString();
                String passTxt = pass.getText().toString();
                String com_passTxt = com_pass.getText().toString();

                if(!emailTxt.matches(emailpattern)){
                    email.setError("Invalid email");
                }
                else if(passTxt.isEmpty() || passTxt.length() < 6)
                    pass.setError("Invalid Password");
                else if(!passTxt.equals(com_passTxt)){
                    com_pass.setError("Password Not Matched");
                }else{
                    databaseReference = FirebaseDatabase.getInstance().getReference("users/"+emailTxt);
                    mAuth.createUserWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                setactivity();
                                Toast.makeText(RegisterActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }

    private void setactivity() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }

}