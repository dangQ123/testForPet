package edu.njust.testforpet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button accountButton;
    private boolean isAllRightToLoginPhone = false;
    private boolean isAllRightToLoginPass = false;
    SharedPreferences shared ;
    public boolean tryToLogin(String phonenumber , String password){
        if (phonenumber.equals("18866668888")&&password.equals("123456")){
            return true;
        }
        return false;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);
        shared = getSharedPreferences("share" , MODE_PRIVATE);


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

        accountButton = findViewById(R.id.accountbutton);
        accountButton.setOnClickListener(view -> {
            // 这里可以写网络代码 ， 利用okhttp3发往后端
            // 写测试代码
            if (tryToLogin(String.valueOf(editTextPhone.getText()) , String.valueOf(editTextPassword.getText()))){

                SharedPreferences.Editor edit = shared.edit();
                edit.putString("phonenumber",String.valueOf(editTextPhone.getText()));
                edit.putString("password",String.valueOf(editTextPassword.getText()));
                edit.commit();

                Intent intent = new Intent(this , HomePageActivity.class);
                intent.putExtra("phonenumber" , String.valueOf(editTextPhone.getText()));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }


        });

    }
}