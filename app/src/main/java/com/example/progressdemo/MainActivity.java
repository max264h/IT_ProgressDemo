package com.example.progressdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button btn_progressDialogCircle, btn_progressDialogValue, btn_progressBarCircle, btn_progressBarValue;
    private ProgressBar circle,value;
    private Timer timer;
    Integer val = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_progressDialogCircle = findViewById(R.id.btn_progress_circle);
        btn_progressDialogValue = findViewById(R.id.btn_progress_value);
        btn_progressBarCircle = findViewById(R.id.btn_progressBar_circle);
        btn_progressBarValue = findViewById(R.id.btn_progressBar_value);
        circle = findViewById(R.id.progressBarCircle);
        value = findViewById(R.id.progressBarValue);

        circle.setVisibility(View.INVISIBLE);
        value.setVisibility(View.INVISIBLE);

        btn_progressDialogCircle.setOnClickListener(view -> setProgressDialogCircle());
        btn_progressDialogValue.setOnClickListener(view -> setProgressDialogValue());
        btn_progressBarCircle.setOnClickListener(view -> setProgressBarCircle());
        btn_progressBarValue.setOnClickListener(view -> setProgressBarValue());
    }
    private void setProgressDialogCircle(){
        ProgressDialog prog_cir = new ProgressDialog(this);
        prog_cir.setTitle("對話框的進度條(轉圈版)");
        prog_cir.setMessage("等待中．．．");
        prog_cir.show();
    }//設定轉圈版的進度條對話框

    private void setProgressDialogValue(){
        ProgressDialog prog_val = new ProgressDialog(this);
        prog_val.setTitle("對話框的進度條(跑條版)");
        prog_val.setMessage("請稍後．．．");
        prog_val.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        prog_val.setMax(100);
        timer = new Timer();
        // 設定定時任務，每隔0.05秒執行一次
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                prog_val.setProgress(val++);
                if (val > 100){
                    timer.cancel();
                    val = 0;
                    prog_val.dismiss();
                }
            }
        }, 0, 50); // 初始延遲為0毫秒，之後每隔50毫秒執行一次
        prog_val.show();
    }
    private void setProgressBarCircle(){
        if (circle.getVisibility() == View.INVISIBLE){
            circle.setVisibility(View.VISIBLE);
        }
        else {
            circle.setVisibility(View.INVISIBLE);
        }
    }
    private void setProgressBarValue(){
        value.setMax(10);
        value.setVisibility(View.VISIBLE);
        timer = new Timer();
        // 設定定時任務，每隔0.1秒執行一次
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                value.setProgress(val++);
                if (val > 10){
                    timer.cancel();
                    val = 0;
                    value.setVisibility(View.INVISIBLE);
                }
            }
        }, 0, 100); // 初始延遲為0毫秒，之後每隔100毫秒執行一次
    }
}
