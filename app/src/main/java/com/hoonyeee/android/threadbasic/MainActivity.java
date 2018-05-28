package com.hoonyeee.android.threadbasic;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Thread thread = new Count10Thread(handler);
        thread.start();


    }
    //sub thread에서 ui thread접근하기위해
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            progressBar.setVisibility(View.GONE);
        }
    };
}

class Count10Thread extends Thread{
    Handler handler;
    public Count10Thread(Handler handler){
        this.handler = handler;
    }
    public void run(){
        for(int i=0; i<10; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        handler.sendEmptyMessage(0);
    }
}
