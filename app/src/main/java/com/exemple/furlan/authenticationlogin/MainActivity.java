package com.exemple.furlan.authenticationlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRef;

    private TextView welcome;

    private ProgressBar progressDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataBase = FirebaseDatabase.getInstance();
        mRef =  mDataBase.getReference("carros");

        progressDB = findViewById(R.id.progressBD);

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
