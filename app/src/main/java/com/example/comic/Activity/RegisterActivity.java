package com.example.comic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comic.R;
import com.example.comic.Retrofit.ApiComic;
import com.example.comic.Retrofit.RetrofitClient;
import com.example.comic.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {
  EditText email,pass,username,fullname;
  AppCompatButton btnRegister;
  ApiComic apiComic;
  CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initControll();
    }

    private void initControll() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_username = username.getText().toString().trim();
        String str_fullname = fullname.getText().toString().trim();
        if(TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(),"Email không được để trống",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(str_pass)) {
            Toast.makeText(getApplicationContext(), "Pass không được để trống", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(str_username)) {
            Toast.makeText(getApplicationContext(), "Username không được để trống", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(str_fullname)) {
            Toast.makeText(getApplicationContext(), "Fullname không được để trống", Toast.LENGTH_LONG).show();
        }else {
            compositeDisposable.add(apiComic.register(str_email,str_pass,str_username,str_fullname)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            userModel -> {
                                if(userModel.isSuccess()){
                                    Utils.user_current.setEmail(str_email);
                                    Utils.user_current.setPass(str_pass);
                                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),userModel.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            },throwable -> {
                                Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                            }
                    ));
        }
    }

    private void initView() {
        apiComic = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiComic.class);
        email = findViewById(R.id.txtemail);
        pass = findViewById(R.id.txtpass);
        username = findViewById(R.id.txtUser);
        fullname = findViewById(R.id.txtFull);
        btnRegister = findViewById(R.id.btnRegister);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}