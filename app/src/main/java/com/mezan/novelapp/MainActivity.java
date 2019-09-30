package com.mezan.novelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ListView novelList,adultList;
    MyAdapter adapter,adapter2;
    String[] novelArray,adultArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_main);

        novelList = findViewById(R.id.novelList);
        adultList = findViewById(R.id.adultList);
        novelArray = getResources().getStringArray(R.array.novellist);
        adultArray = getResources().getStringArray(R.array.adultslist);
        adapter = new MyAdapter(novelArray,this);
        adapter2 = new MyAdapter(adultArray,this);
        novelList.setAdapter(adapter);
        adultList.setAdapter(adapter2);


        novelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent novelIT = new Intent(MainActivity.this, ReadingActivity.class);
                novelIT.putExtra("index",i);
                novelIT.putExtra("title",novelArray[i]);
                novelIT.putExtra("type","novel");
                startActivity(novelIT);
            }
        });

        adultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent adultIT = new Intent(MainActivity.this, ReadingActivity.class);
                adultIT.putExtra("index",i);
                adultIT.putExtra("title",adultArray[i]);
                adultIT.putExtra("type","adult");
                startActivity(adultIT);
            }
        });

    }
}
