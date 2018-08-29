package com.exemple.furlan.authenticationlogin;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class RegistrationActivity extends Activity {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase mDataBase;
    private DatabaseReference mRef;

    EditText editNome;
    EditText editDataNascimento;
    EditText editIdioma;
    EditText editLogin;
    EditText editSenha;
    private Spinner spinnerGraduacao;

    ProgressDialog progressLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        mDataBase = FirebaseDatabase.getInstance();
        mRef =  mDataBase.getReference("usuarios");

        editNome = findViewById(R.id.rNome);
        editDataNascimento = findViewById(R.id.rDtNascimento);
        spinnerGraduacao = findViewById(R.id.spinnerGraduacao);
        editIdioma = findViewById(R.id.rIdioma);
        editLogin = findViewById(R.id.rEmail);
        editSenha = findViewById(R.id.rPassword);
        progressLogin = new ProgressDialog(this);
    }

    public void onStart() {
        super.onStart();
        editDataNascimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });
    }

    public void confirmar(View view) {
        String email = editLogin.getText().toString().trim();
        String senha  = editSenha.getText().toString().trim();

        final String nome = editNome.getText().toString().trim();
        final String dtNascimento = editDataNascimento.getText().toString();
        final String graduacao = spinnerGraduacao.getSelectedItem().toString();
        final String idioma = editIdioma.getText().toString();

        if(TextUtils.isEmpty(nome)){
            Toast.makeText(this, "Informe seu nome",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(dtNascimento)){
            Toast.makeText(this, "Informe sua data de nascimento",Toast.LENGTH_LONG).show();
            return;
        }
        if(graduacao.contains("informe")){
            Toast.makeText(this, "Informe sua graduação",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(idioma)){
            Toast.makeText(this, "Informe seu idioma",Toast.LENGTH_LONG).show();
            return;
        }
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
                    addInfoUser(nome, dtNascimento, graduacao, idioma);

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

    public void addInfoUser(String nome, String dtNascimento, String graduacao, String idioma){
        mUser = mAuth.getCurrentUser();

        if (mUser != null) {
            Usuario usuario = new Usuario(nome, dtNascimento, graduacao, idioma);

            mRef.child(mUser.getUid()).setValue(usuario).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this, "Usuário registrado com suscesso",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }











    }
}
