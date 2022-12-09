package com.example.comic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.comic.Model.Comic;
import com.example.comic.R;

import java.text.DecimalFormat;

public class ChiTietActivity extends AppCompatActivity {
 TextView name, authur, year , content;
 AppCompatButton btnRead , btnComment;
 ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        initView();
        initData();
    }

    private void initData() {
        Comic comic =(Comic) getIntent().getSerializableExtra("chitiet");
        name.setText(comic.getName());
        authur.setText(comic.getAuthor());
        year.setText(comic.getYear());
        
        content.setText(comic.getContent());
        Glide.with(getApplicationContext()).load(comic.getAvatar()).into(imgAvatar);

    }

    private void initView() {
        name = findViewById(R.id.tvnameCT);
        authur = findViewById(R.id.tvAthurCT);
        year = findViewById(R.id.tvYearCT);
        content = findViewById(R.id.tvContent);
        btnRead = findViewById(R.id.btnread);
        btnComment = findViewById(R.id.btncoment);
        imgAvatar = findViewById(R.id.imgAvatar);
    }
}