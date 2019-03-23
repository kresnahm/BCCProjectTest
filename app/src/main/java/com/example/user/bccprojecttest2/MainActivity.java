package com.example.user.bccprojecttest2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText emailLogin, passLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.textView3).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        emailLogin = (EditText) findViewById(R.id.emailLogin);
        passLogin = (EditText) findViewById(R.id.passLogin);

    }

    private void userLogin(){
        String email = emailLogin.getText().toString().trim();
        String password = passLogin.getText().toString().trim();


        if (email.isEmpty()){
            emailLogin.setError("Email tidak boleh dikosongkan");
            emailLogin.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailLogin.setError("Masukan alamat email yang valid");
            emailLogin.requestFocus();
            return;
        }
        if (password.isEmpty()){
            passLogin.setError("Password tidak boleh kosong");
            passLogin.requestFocus();
            return;
        }
        if(password.length() < 8){
            passLogin.setError("Password kurang dari 8 karakter");
            passLogin.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login Berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this , HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView3:
                startActivity(new Intent(this,RegisterActivity.class));
                break;

            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }
}
