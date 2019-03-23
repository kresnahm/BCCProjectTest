package com.example.user.bccprojecttest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class UserProfileActivity extends AppCompatActivity {

    private TextView namaTextView, jkTextView, umurTextView, emailTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        namaTextView =(TextView) findViewById(R.id.namaTextView);
        jkTextView = (TextView) findViewById(R.id.jkTextView);
        umurTextView = (TextView) findViewById(R.id.umurTextView);
        emailTextView = (TextView) findViewById(R.id.namaTextView);

//        namaTextView.setText(FirebaseDatabase.getInsta);
    }

}
