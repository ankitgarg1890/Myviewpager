package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<MyModel> myModelArrayList;

    public MyAdapter(Context context, ArrayList<MyModel> myModelArrayList) {
        this.context = context;
        this.myModelArrayList = myModelArrayList;
    }


    @Override
    public int getCount() {
        return myModelArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        Log.d("positonss", "instantiateItem: "+position);
        View view = LayoutInflater.from(context).inflate(R.layout.card_view,container,false);

        ImageView imge= view.findViewById(R.id.imge);
        TextView ttext =  view.findViewById(R.id.ttext);
        TextView dtext  = view.findViewById(R.id.dtext);
        TextView datetext= view.findViewById(R.id.datetext);
//get data-------

        MyModel myModel= myModelArrayList.get(position);

        String title= myModel.getTitle();
        String description = myModel.getDescription();
        String date= myModel.getDate();

        int image = myModel.getImage();

        //set data-----

        imge.setImageResource(image);
        ttext.setText(title);
        dtext.setText(description);
        ttext.setText(title);
        datetext.setText(date);
        // handler card

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,title+ "/n"+description+ "/n"+date,Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view,position-1);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
