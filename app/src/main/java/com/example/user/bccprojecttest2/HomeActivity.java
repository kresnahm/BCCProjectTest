package com.example.user.bccprojecttest2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.openUserProfile).setOnClickListener(this);
        findViewById(R.id.logOutButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logOutButton:

                break;
            case R.id.openUserProfile:
                startActivity(new Intent(this,UserProfileActivity.class));
                break;
        }
    }
}
