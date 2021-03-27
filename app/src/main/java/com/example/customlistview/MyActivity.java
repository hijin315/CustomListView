package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MyActivity extends AppCompatActivity {
    ArrayList<Voca> arr = new ArrayList<>();
    GridView list;

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        list = findViewById(R.id.list);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR); //현재 세팅된 년도
        int month = cal.get(Calendar.MONTH); //현재 세팅된 월 0~11
        int date = cal.get(Calendar.DATE); //현재 세팅된 일
        cal.add(Calendar.DATE, -10); //현재 세팅된 일에서 10일전
        cal.set(Calendar.DATE, 10); //현재 10일 세팅
        int maxdate = cal.getActualMaximum(Calendar.DATE);
       //temp = cal.get(Calendar.DAY_OF_WEEK); //1 일요일 ~ 7 토요일


        adapter = new MyAdapter(this);
        list.setAdapter(adapter);

        init();

        adapter.notifyDataSetChanged();
    }

    private void init(){
        for (int i = 0; i < 42; i++) {
            arr.add(new Voca("eng "+i," kor "+i));
        }
    }

    class MyAdapter extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter(Activity context) {
            super(context, R.layout.item2, arr);
            lnf = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return arr.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return arr.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null) {
                convertView = lnf.inflate(R.layout.item2, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.nameTv = convertView.findViewById(R.id.name_tv);


                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.nameTv.setText(arr.get(position).eng + ":"+arr.get(position).kor);

            if(position % 2 ==1){
                viewHolder.iconIv.setVisibility(View.INVISIBLE);
            }else{
                viewHolder.iconIv.setVisibility(View.VISIBLE);
            }

            return convertView;
        }
    }

    class ViewHolder {
        TextView nameTv;
        ImageView iconIv;
    }
}