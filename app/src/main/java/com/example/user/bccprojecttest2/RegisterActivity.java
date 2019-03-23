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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText emailRegist, passRegist, namaRegist, jkRegist, umurRegist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_register);

        emailRegist = (EditText)findViewById(R.id.emailRegist);
        passRegist  = (EditText) findViewById(R.id.passRegist);
        namaRegist = (EditText) findViewById(R.id.namaRegist);
        jkRegist = (EditText) findViewById(R.id.jkRegist);
        umurRegist = (EditText) findViewById(R.id.umurRegist);

        findViewById(R.id.buttonDaftar).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    public void userRegist(){
        String email = emailRegist.getText().toString().trim();
        String password = passRegist.getText().toString().trim();
        final int umur = Integer.parseInt(umurRegist.getText().toString().trim());
        final String nama = namaRegist.getText().toString().trim();
        final String jenisKelamin = jkRegist.getText().toString().trim();

        if (email.isEmpty()){
            emailRegist.setError("Email tidak boleh dikosongkan");
            emailRegist.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailRegist.setError("Masukan alamat email yang valid");
            emailRegist.requestFocus();
            return;
        }
        if (password.isEmpty()){
            passRegist.setError("Password tidak boleh kosong");
            passRegist.requestFocus();
            return;
        }
        if(password.length() < 8){
            passRegist.setError("Password kurang dari 8 karakter");
            passRegist.requestFocus();
            return;
        }
        if(umur>100){
            umurRegist.setError("Masukan umur yang valid");
            umurRegist.requestFocus();
            return;
        }
        if(nama.isEmpty()){
            namaRegist.setError("Nama tidak boleh kosong");
            namaRegist.requestFocus();
            return;
        }
        if(jenisKelamin.length() >1){
            jkRegist.setError("Masukan jenis kelamin yang valid (L/P)");
            jkRegist.requestFocus();
            return;
        }
        if(jenisKelamin.equalsIgnoreCase("L") || jenisKelamin.equalsIgnoreCase("P")){

        }else{
            jkRegist.setError("Masukan jenis kelamin yang valid (L/P)");
            jkRegist.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                    Toast.makeText(getApplicationContext(),"Pengguna Terdaftar",Toast.LENGTH_SHORT).show();

                    Users user = new Users(nama, umur,jenisKelamin);
//                    Map newPost = new HashMap();
//                    newPost.put("nama",nama);
//                    newPost.put("umur",umur);
//                    newPost.put("jenisKelamin",jenisKelamin);

                    current_user_db.setValue(user);
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Akun dengan E-mail ini sudah terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDaftar:
                userRegist();
//                startActivity(new Intent(this,HomeActivity.class));
//                finish();
                break;

            case R.id.textViewLogin:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}
