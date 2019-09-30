package com.mezan.novelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    //List<CountryObject> Country = new ArrayList<>();
    String []name = new String[250];
    Context context;
    public  MyAdapter(){

    }
    public MyAdapter(String []name ,Context context){
        this.name = name;
        this.context = context;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup root) {
       view= LayoutInflater.from(context).inflate(R.layout.mainlistlayout,root,false);
       TextView Name;
     Name = view.findViewById(R.id.item_name);
        Name.setText(name[i]);
        return view;
    }
}