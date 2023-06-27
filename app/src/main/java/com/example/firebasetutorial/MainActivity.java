package com.example.firebasetutorial;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button Logout;
    FirebaseUser User;
    TextView text_user;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        User=mAuth.getCurrentUser();
        Logout=findViewById(R.id.Logout);
        text_user=findViewById(R.id.text);
        if (User==null){
            Intent intent=new Intent(getApplicationContext(),Login_Activity.class);
            startActivity(intent);
            finish();
        }
        else {
            text_user.setText(User.getEmail());
        }
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}