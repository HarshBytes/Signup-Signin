package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration_Activity extends AppCompatActivity {
    EditText Email,Password;
    Button Signup;
    TextView Login;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        Email=findViewById(R.id.REmail);
        Password=findViewById(R.id.RPassword);
        Signup=findViewById(R.id.RSignup);
        Login=findViewById(R.id.RLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Registration_Activity.this, "heyyyyy", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(intent);
                finish();

            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                try {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(Registration_Activity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Registration_Activity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        Log.e("Registration", "Registration failed: " + task.getException());
                                    }
                                }
                            });
                } catch (Exception e) {
                    // Catch any exceptions that occur during the registration process
                    Toast.makeText(Registration_Activity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                    Log.e("Registration", "Exception: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}