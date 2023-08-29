package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SleepActivity extends AppCompatActivity {

    private Button backicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        backicon = findViewById(R.id.backicon);
        backicon.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(new Intent(this , HomePageActivity.class));
            overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_from_right);
        });

    }
}