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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**

     1. item을 위한 레이아웃을 만든다
     2. item을 위한 holder 클래스를 만든다
     3. item 을 위한 데이터 클래스를 만든다
     4. [3]번의 클래스가 들어가는 어레이리스트를 만든다
     5. 아답터를 복붙하고 빨간색 없애기
     6. 아답터 초기화 해서 리스트뷰에 붙이기

     **/
    ArrayList<Voca> vocaArr = new ArrayList<>();
    MyAdapter adapter;
    GridView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.list);
        adapter = new MyAdapter(this);

        String str = "abcdefg";

        str = str.substring(1); //bcdefg
        str = str.substring(2,5); //def

        str.indexOf("e");  //1
        str = str.replace("a","b");


        list.setAdapter(adapter);

        for (int i = 0; i < 30; i++) {
            vocaArr.add(new Voca("dog "+i, "개"));
        }
        adapter.notifyDataSetChanged();
    }

    class MyAdapter extends ArrayAdapter {
        LayoutInflater lnf;

        public MyAdapter(Activity context) {
            super(context, R.layout.item, vocaArr);
            lnf = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return vocaArr.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return vocaArr.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ItemHolder viewHolder;
            if(convertView == null) {
                convertView = lnf.inflate(R.layout.item, parent, false);
                viewHolder = new ItemHolder();
                viewHolder.nameTvHolder1 = convertView.findViewById(R.id.name_tv);
                viewHolder.nameTvHolder2 = convertView.findViewById(R.id.name_tv2);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ItemHolder)convertView.getTag();
            }

            viewHolder.nameTvHolder1.setText(vocaArr.get(position).eng);
            viewHolder.nameTvHolder2.setText(vocaArr.get(position).kor);

            return convertView;
        }
    }

    class ItemHolder{
        TextView nameTvHolder1;
        TextView nameTvHolder2;
    }
}