package marco.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Boolean timerIsActive = false;
    Button timerButton;
    CountDownTimer countDownTimer;


    /**
     *
     * METHODS
     */

    public void resetTimer(){

        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        timerButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        timerIsActive = false;

    }


    public void updateTime (int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);
        if (secondString == "0") {
            secondString = "00";
        }else if(secondString.length() == 1){
            secondString = "0"+secondString;
        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }



    public void controlTimer(View view) {

        if (timerIsActive == false){

            timerIsActive = true;

        timerSeekBar.setEnabled(false);
        timerButton.setText("Stop");

        countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                updateTime((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {

                resetTimer();
                timerTextView.setText("0:00");
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.war);
                mplayer.start();
                Log.i("Finish", "Timer done!!!");

            }
        }.start();

        Log.i("GO Button", "clicked");

    } else {

            resetTimer();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerButton = findViewById(R.id.controllerButton);
        timerTextView = findViewById(R.id.timerTextView);
        timerSeekBar = findViewById(R.id.timerSeekBar);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTime(progress);
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
