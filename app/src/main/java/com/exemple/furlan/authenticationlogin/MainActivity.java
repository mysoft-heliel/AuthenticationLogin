package com.exemple.furlan.authenticationlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    private String teste;
    private String teste2;

    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.text_welcome);

        mAuth = FirebaseAuth.getInstance();

        mUser = mAuth.getCurrentUser();

        if (mUser != null) {
            welcome.setText("Bem vindo "+mUser.getEmail());
        } else {
            logar();
        }
    }

    public void sair(View view) {
        FirebaseAuth.getInstance().signOut();

        logar();
    }

    private void logar() {
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }
}
