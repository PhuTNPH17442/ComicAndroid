package com.example.comic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comic.R;
import com.example.comic.Retrofit.ApiComic;
import com.example.comic.Retrofit.RetrofitClient;
import com.example.comic.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    TextView txtDangky;
    EditText email,pass;
    AppCompatButton btnLogin;
    ApiComic apiComic;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initControl();
    }

    private void initControl() {
        txtDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email = email.getText().toString().trim();
                String str_pass = pass.getText().toString().trim();
                if(TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(),"Email không được để trống",Toast.LENGTH_LONG).show();
                }else if(TextUtils.isEmpty(str_pass)) {
                    Toast.makeText(getApplicationContext(), "Pass không được để trống", Toast.LENGTH_LONG).show();
                }else{
                    compositeDisposable.add(apiComic.login(str_email,str_pass)

                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if (userModel.isSuccess()) {
                                            Utils.user_current = userModel.getResult().get(0);
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Log.d("loggg", userModel.getMessage());
                                        }
                                    },
                                    throwable -> {
                                        Log.d("loggg", throwable.getMessage());

                                        Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                                    }
                            ));
                }
            }
        });
    }

    private void initView() {
        apiComic = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiComic.class);
        txtDangky = findViewById(R.id.txtDangky);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnlogin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user_current.getEmail() != null && Utils.user_current.getPass() != null){
            email.setText(Utils.user_current.getEmail());
            pass.setText(Utils.user_current.getPass());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }
}