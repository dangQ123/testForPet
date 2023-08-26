package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class PhoneVerifyActivity extends AppCompatActivity {

    private EditText editText;
    private Button accountbutton;
    private boolean isAllRightToLoginPhone = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);

        editText = findViewById(R.id.phonenum);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入字符的数目
                int length = s.length();
                if (length == 11){
                    isAllRightToLoginPhone = true;
                    if (isAllRightToLoginPhone){
                        Button button = findViewById(R.id.accountbutton);
                        Drawable drawable = getResources().getDrawable(R.drawable.phoneloginretangle);
                        button.setTextColor(Color.WHITE);
                        button.setBackground(drawable);
                        button.setEnabled(true);
                    }
                }
                else {
                    isAllRightToLoginPhone = false;
                    Button button = findViewById(R.id.accountbutton);
                    Drawable drawable = getResources().getDrawable(R.drawable.accountloginretangle);
                    button.setBackground(drawable);
                    button.setEnabled(false);
                    button.setTextColor(getApplication().getResources().getColor(R.color.grey_login));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        accountbutton = findViewById(R.id.accountbutton);
        accountbutton.setOnClickListener(view -> {
            Intent intent = new Intent(this , InputVerifyActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("phonenumber", String.valueOf(editText.getText()));
            intent.putExtras(bundle);
            startActivity(intent);
        });


    }
}