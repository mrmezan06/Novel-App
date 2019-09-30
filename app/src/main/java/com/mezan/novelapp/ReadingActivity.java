package com.mezan.novelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadingActivity extends AppCompatActivity {

    int[] novelResImg;
    int[] adultResImg;
    String[] novelFileName;
    String[] adultFileName;

    TextView titleTXT,contentTXT;
    ImageView feature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_reading);

        novelResImg = new int[]{R.drawable.ea,R.drawable.eb,R.drawable.ec,R.drawable.ed,R.drawable.ee,R.drawable.ef,R.drawable.eg,R.drawable.eh};
        adultResImg = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};

        novelFileName = getResources().getStringArray(R.array.novelFileName);
        adultFileName = getResources().getStringArray(R.array.adultFileName);

        feature = findViewById(R.id.featureImg);
        titleTXT = findViewById(R.id.titleContent);
        contentTXT = findViewById(R.id.Main_content);

        Intent it = getIntent();
        Bundle bd = it.getExtras();
        if(bd != null){
            int index = bd.getInt("index");
            String title = bd.getString("title");
            String type = bd.getString("type");
            if (type.equals("novel")){
                String file = novelFileName[index];
                String content = readFromAssets(this,file);

                //set here title and content
                titleTXT.setText(title);
                contentTXT.setText(content);
                feature.setImageResource(novelResImg[index]);

            }else if (type.equals("adult")){
                String file = adultFileName[index];
                String content = readFromAssets(this,file);
                titleTXT.setText(title);
                contentTXT.setText(content);
                feature.setImageResource(adultResImg[index]);
            }
        }
    }

    public  String readFromAssets(Context context,String filename) {

        String ret = "";
        AssetManager mgr = context.getAssets();


        try {
            InputStream in = mgr.open(filename, AssetManager.ACCESS_BUFFER);

            if ( in != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                in.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {

            Log.e("Reading File", "File not found: " + e.toString());
            Toast.makeText(getApplicationContext(),"File Missing!",Toast.LENGTH_LONG).show();
            return null;
        } catch (IOException e) {
            Log.e("Reading File", "Can not read file: " + e.toString());
            Toast.makeText(getApplicationContext(),"File Missing from data!",Toast.LENGTH_LONG).show();
            return null;
        }

        return ret;
    }
}
