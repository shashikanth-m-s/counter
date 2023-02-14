package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="thread" ;
    Handler mainHandler=new Handler();
    Button stbtn,stpbtn;
    TextView ctrvalue;
    int count;
    boolean running=false;

    void startThread(){
    NewThread nobj=new NewThread();
    nobj.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stbtn = findViewById(R.id.stbtn);
        stpbtn = findViewById(R.id.stpbttn);
        ctrvalue = findViewById(R.id.ctrvalue);

        stbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=true;
                startThread();
            }
        });

        stpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=false;
            }
        });

    }
    class NewThread extends Thread{
        @Override
        public void run() {
           while(running){
               count++;

               mainHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       ctrvalue.setText(String.valueOf(count));
                   }
               });
                Log.d(TAG, "startThread: "+count);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

