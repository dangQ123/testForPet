package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPhoneActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextPassword;
    private boolean isAllRightToLoginPhone = false;
    private boolean isAllRightToLoginPass = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);
        editTextPhone = findViewById(R.id.phonenum);
        editTextPassword = findViewById(R.id.passwordaccount);
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入字符的数目
                int length = s.length();
                if (length == 11){
                    isAllRightToLoginPhone = true;
                    if (isAllRightToLoginPhone && isAllRightToLoginPass){
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
        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入字符的数目
                int length = s.length();
                if (length >= 6&&length<=14){
                    isAllRightToLoginPass = true;
                    if (isAllRightToLoginPhone && isAllRightToLoginPass){
                        Button button = findViewById(R.id.accountbutton);
                        Drawable drawable = getResources().getDrawable(R.drawable.phoneloginretangle);
                        button.setTextColor(Color.WHITE);
                        button.setBackground(drawable);
                        button.setEnabled(true);
                    }
                }
                else {
                    isAllRightToLoginPass = false;
                    Button button = findViewById(R.id.accountbutton);
                    Drawable drawable = getResources().getDrawable(R.drawable.accountloginretangle);
                    button.setTextColor(getApplication().getResources().getColor(R.color.grey_login));
                    button.setBackground(drawable);
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}