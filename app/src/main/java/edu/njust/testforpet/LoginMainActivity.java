package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class LoginMainActivity extends AppCompatActivity {

    private Button phonelogin,acountloginbutton;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phonelogin = findViewById(R.id.phonelogin);
        phonelogin.setOnClickListener(view -> {
            Intent intent = new Intent(this , PhoneVerifyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        acountloginbutton = findViewById(R.id.acountloginbutton);
        acountloginbutton.setOnClickListener(view -> {
            Intent intent = new Intent(this , LoginPhoneActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

    }
}