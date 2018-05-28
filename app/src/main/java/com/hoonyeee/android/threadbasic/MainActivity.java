package com.hoonyeee.android.threadbasic;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import static com.hoonyeee.android.threadbasic.MainActivity.MSG_HIDE;

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
    final static int MSG_HIDE = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_HIDE:
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}

class Count10Thread extends Thread{
    Handler handler;
    public Count10Thread(Handler handler){
        this.handler = handler;
    }
    public void run(){
        for(int i=0; i<3; i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Message message = new Message();
        message.what = MSG_HIDE;
        message.arg1 = 35;
        handler.sendMessage(message);
        //handler.sendEmptyMessage(0);
    }
}
