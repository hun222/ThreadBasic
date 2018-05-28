package com.hoonyeee.android.threadbasic;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Integer,Integer,Boolean>(){
            @Override
            protected void onPreExecute() {
                //백그라운드 동작전에 세팅
                super.onPreExecute();
                progressBar = findViewById(R.id.progressBar);
                tv = findViewById(R.id.tv);
            }

            @Override
            protected Boolean doInBackground(Integer... args) {//Thread의 run
                int time = 0;
                if(args!=null && args.length >0)
                    time = args[0];
                for(int i=0; i<=10; i++){
                    sleep(time);
                    publishProgress(i); //onProgressUpdate호출
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                if(result){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                tv.setText(values[0]+"");
            }

            public void sleep(long time){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.execute(5);


    }

}


