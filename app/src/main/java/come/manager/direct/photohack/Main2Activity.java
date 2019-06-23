package come.manager.direct.photohack;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final String[] IMAGES= {"","","","",""};
    private static final Integer[] DATE= {1970,1980,1990,2000};
    private ArrayList<String> ImagesArray = new ArrayList<String>();
    private ArrayList<Integer> dATESArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String urls = getIntent().getStringExtra("url");
String[] url = urls.split(",");
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson =builder.create();
//        Example url = gson.fromJson(urls, Example.class);
        for(int i = 0; i < IMAGES.length; i++) {
            IMAGES[i] = url[i];
        }
        init();
        Button button = findViewById(R.id.exportbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);
        for(int i=0;i<DATE.length;i++)
            dATESArray.add(DATE[i]);
        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(Main2Activity.this,ImagesArray, dATESArray));






        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius


        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3_000, 3_000);

        // Pager listener over indicator


    }

}