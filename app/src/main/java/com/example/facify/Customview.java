package com.example.facify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Customview extends BaseAdapter {

    Context context;
    ArrayList<String> L_name;
    public Customview(Context ctx, ArrayList<String> name){
        this.context = ctx;
        this.L_name = name;
    }

    @Override
    public int getCount() {
        return L_name.size();
    }

    public void SearchDataList(ArrayList<String> name){
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.recycler_item,null);
        TextView textView = (TextView) convertView.findViewById(R.id.recTitle);
        textView.setText(L_name.get(position));
        return convertView;
    }
}
