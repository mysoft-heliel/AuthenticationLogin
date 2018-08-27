package com.exemple.furlan.authenticationlogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends Activity {
    private FirebaseAuth mAuth;

    EditText editLogin;
    EditText editSenha;

    ProgressDialog progressLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        editLogin = findViewById(R.id.rEmail);
        editSenha = findViewById(R.id.rPassword);
        progressLogin = new ProgressDialog(this);
    }

    public void confirmar(View view) {
        String email = editLogin.getText().toString().trim();
        String senha  = editSenha.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Informe seu e-mail",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(senha)){
            Toast.makeText(this, "Informe sua senha",Toast.LENGTH_LONG).show();
            return;
        }

        progressLogin.setMessage("Registrando usuário");
        progressLogin.show();

        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Usuário registrado com suscesso",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(RegistrationActivity.this, "Não foi possível registrar o usuário",Toast.LENGTH_LONG).show();
                }
                progressLogin.dismiss();
            }
        });
    }
}
