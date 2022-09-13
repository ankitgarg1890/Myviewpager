package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
ViewPager viewPager;
DotsIndicator dotsIndicator;
private MyAdapter myAdapter;
private ArrayList<MyModel> myModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         viewPager = findViewById(R.id.viewp);
dotsIndicator= findViewById(R.id.dots_indicator);
         loadCard();


         viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 String title= myModelArrayList.get(position).getTitle();
             }

             @Override
             public void onPageSelected(int position) {

             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });
}

private void loadCard(){

    myModelArrayList = new ArrayList<>();
    myModelArrayList.add(new MyModel(

            "Android",
            "Google Play Protect, regular security updates and control over how your data is shared. We’re dedicated to securing Android’s 2.5 billion+ active devices every day and keeping information private.",
            "04/04/22",R.drawable.image24));
    myModelArrayList.add(new MyModel(

            ".NET ",
            "The newest OS updates. The biggest announcements. The most recent platform news. If it’s new in the world of Android, you can find it here.",
            "05/04/22",R.drawable.img22));
    myModelArrayList.add(new MyModel(

            "JAVA",
            "Java is a popular programming language.Java is used to develop mobile apps, web apps, desktop apps, games and much more.",
            "06/04/22",R.drawable.imag23));
    myModelArrayList.add(new MyModel(

            "Python",
            "Java is a popular programming language.Java is used to develop mobile apps, web apps, desktop apps, games and much more.",
            "07/04/22",R.drawable.i14));
    myModelArrayList.add(new MyModel(

            "C",
            "Java is a popular programming language.Java is used to develop mobile apps, web apps, desktop apps, games and much more.",
            "08/04/22",R.drawable.i16));

    myModelArrayList.add(new MyModel(
            "C++",
            "Java is a popular programming language.Java is used to develop mobile apps, web apps, desktop apps, games and much more.",
            "09/04/22",R.drawable.i15));

myAdapter= new MyAdapter(this,myModelArrayList);
viewPager.setAdapter(myAdapter);
dotsIndicator.setViewPager(viewPager);
viewPager.setPadding(100,0,100,0);



}

}