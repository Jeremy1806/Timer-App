package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekbar;
    TextView editTextTime;
    int timeInSec ;
    int flag = 0 ;
    Button stopButton;
    CountDownTimer yourCountDownTimer;

    public void playSound(){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.rooster);
        mediaPlayer.start();
    }

    public void stopButton(View view) {

        if(flag ==0) {
            stopButton.setText("STOP");
            timerSeekbar.setEnabled(false);
            flag=1;
            Log.i("Time is :", Integer.toString(timeInSec));
            long sec = timeInSec*1000;
            yourCountDownTimer= new CountDownTimer(sec+100,1000){
                public void onTick(long milliSecondsUntilDone){
                    long minutes,seconds;

                    minutes = milliSecondsUntilDone/60000;
                    seconds = (milliSecondsUntilDone/1000)-minutes*60;

                    editTextTime.setText(String.format("%02d:%02d", minutes, seconds));
                }
                public void onFinish(){
                    editTextTime.setText("00:00");
                    playSound();
                }
            }.start();
        }
        else{
            stopButton.setText("START");
            yourCountDownTimer.cancel();
            timerSeekbar.setEnabled(true);
            flag=0;
        }


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekbar = findViewById(R.id.timerSeekBar);
        stopButton = (Button) findViewById(R.id.stopButton);

        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);
        editTextTime = (TextView)findViewById(R.id.editTextTime);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes=0,seconds=0;
                timeInSec = progress;
                minutes = timeInSec/60;

                seconds= timeInSec-minutes*60;
                editTextTime.setText(String.format("%02d:%02d", minutes, seconds));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}