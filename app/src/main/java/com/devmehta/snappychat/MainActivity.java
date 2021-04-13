package com.devmehta.snappychat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devmehta.snappychat.Authentication.EmailNotVerified;
import com.devmehta.snappychat.Authentication.LoginActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            final FirebaseUser user = mAuth.getCurrentUser();
            TextView helloWorld = findViewById(R.id.hello);
            helloWorld.setText("Hello, " + user.getEmail() + "😊");
            Button btn = findViewById(R.id.logout);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAuth.signOut();
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            user.reload().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    if(!user.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this, EmailNotVerified.class));
                        finish();
                    }
                }
            });
        }
    }
}