package com.example.comic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.comic.Adapter.ComicAdapter;
import com.example.comic.Model.Comic;
import com.example.comic.Model.ComicModel;
import com.example.comic.R;
import com.example.comic.Retrofit.ApiComic;
import com.example.comic.Retrofit.RetrofitClient;
import com.example.comic.Utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
     Toolbar toolbar;
     ViewFlipper viewFlipper;
     RecyclerView recyclerViewHome;
     NavigationView navigationView;
     ListView listViewHome;
     DrawerLayout drawerLayout;
     List<Comic> comicList;
     CompositeDisposable compositeDisposable = new CompositeDisposable();
     ApiComic apiComic;
     List<ComicModel> arrayComic;
     ComicAdapter comicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiComic = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiComic.class);
        //ActionViewFlipper();
        AnhXa();
        getComic();
    }
    private void getComic(){
          compositeDisposable.add(apiComic.getComic()
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(
                     comicModel -> {
                         if(comicModel.isSuccess()){
                             comicList = comicModel.getResult();
                             comicAdapter = new ComicAdapter(getApplicationContext(),comicList);
                             recyclerViewHome.setAdapter(comicAdapter);
                         }
                     },
                          throwable -> {
                              Toast.makeText(getApplicationContext(),"Don't connected"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                          }
                  ));

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarHome);

        recyclerViewHome = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setHasFixedSize(true);
        navigationView = findViewById(R.id.navigationview);
        listViewHome = findViewById(R.id.lvhome);
        drawerLayout = findViewById(R.id.drawerLayout);
        comicList = new ArrayList<>();
        arrayComic = new ArrayList<>();

    }



    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}