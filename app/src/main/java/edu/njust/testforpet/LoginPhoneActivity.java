package edu.njust.testforpet;

import androidx.annotation.NonNull;
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

import com.google.gson.Gson;

import java.io.IOException;

import edu.njust.testforpet.entity.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginPhoneActivity extends AppCompatActivity {

    private int resForLogin = -2;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private Button accountButton;
    private boolean isAllRightToLoginPhone = false;
    private boolean isAllRightToLoginPass = false;
    private final static String URL_STOCK = "http://192.168.1.108:8080/login";
    SharedPreferences shared ;
    public int loginForPet(String phonenumber , String password , String URL){

//

        User user = new User();
        user.setPhonenumber(phonenumber);
        user.setPassword(password);

        String jsonStr = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(jsonStr, MediaType.parse("application/json; charset=utf-8")
        );

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //.get() // 因为OkHttp默认采用get方式，所以这里可以不调get方法
                .post(requestBody)
                .url(URL) // 指定http请求的调用地址
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("失败了失败了");
//                runOnUiThread(()->{
//                    textView.setText("失败了失败了");
//                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String resp = response.body().string();
//                runOnUiThread(()->{
//                    textView.setText(resp);
//                });
                if (resp.equals("0")){
                    resForLogin = 0;
                }else {
                    resForLogin = -1;
                }
            }
        });
        while (resForLogin == -2){

        }
        return resForLogin;
    }
    public boolean tryToLogin(String phonenumber , String password , String URL){
        if (phonenumber.equals("18866668888")&&password.equals("123456")){
            return true;
        }
        int res = loginForPet(phonenumber, password, URL);
        if (res == 0) return true;
        else return false;
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
            String phonenumber_text = String.valueOf(editTextPhone.getText());
            String password_text = String.valueOf(editTextPassword.getText());

            if (tryToLogin(phonenumber_text , password_text,URL_STOCK)){

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

        Button backicon = findViewById(R.id.backicon);
        backicon.setOnClickListener(view -> {
            Intent intent = new Intent(this , LoginMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }
}