package com.example.alam.pvsatest;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

/**
 * Created by alam on 14/12/17.
 */

public class PacedVisualFragment extends Fragment {
    View v;
    ProgressBar progressBar,randomNumberProgress;
    TextView timerView,label,randomNumberDisplay,randomNumberLabel;
    int timerCount = 5;
    int gameLevel = 0;
    int firstNumber,secondNumber,sum = 0;
    int numberCount = 0;
    boolean isFirstTimerFinished = false;
    final Handler handler = new Handler();
    private TextToSpeech mTextToSpeech;
    private Boolean isText= false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_paced_visual, container, false);
        progressBar  = (ProgressBar) v.findViewById(R.id.progress_bar);
        randomNumberProgress = (ProgressBar) v.findViewById(R.id.random_number_progress);
        randomNumberLabel = (TextView) v.findViewById(R.id.random_number_label);
        randomNumberDisplay = (TextView) v.findViewById(R.id.random_number);
        timerView = (TextView) v.findViewById(R.id.timer_view);
        label = (TextView) v.findViewById(R.id.label);
       // instantiateTextToSpeech();
        progressBar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.buttonColor), android.graphics.PorterDuff.Mode.SRC_IN);
        randomNumberProgress.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.buttonColor), android.graphics.PorterDuff.Mode.SRC_IN);
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf(timerCount));
                startAnimator(progressBar,5000);
                timerCount--;
            }

            @Override
            public void onFinish() {
                timerView.setText("0");
                progressBar.setVisibility(View.GONE);
                timerView.setVisibility(View.GONE);
                label.setVisibility(View.GONE);
                randomNumberProgress.setVisibility(View.VISIBLE);
                randomNumberDisplay.setVisibility(View.VISIBLE);
                randomNumberLabel.setVisibility(View.VISIBLE);
                firstNumber = randInt(0, 8);
                if(isText){
                    randomNumberDisplay.setText(String.valueOf(firstNumber));

                }else {
                    instantiateTextToSpeech();
                    mTextToSpeech.speak(String.valueOf(firstNumber), TextToSpeech.QUEUE_FLUSH, null);

                }

                startNumberProgress();
                generateRandomNumber();

            }

            }.start();



        return v;
    }
    public void generateRandomNumber(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               secondNumber = randInt(0, 8);
                if(isText){
                    randomNumberDisplay.setText(String.valueOf(secondNumber));

                }else {
                    mTextToSpeech.speak(String.valueOf(secondNumber), TextToSpeech.QUEUE_FLUSH, null);

                }

                if(numberCount < 5) {
                    handler.postDelayed(this, 3000);
                }
                numberCount++;
            }
        };
        handler.postDelayed(runnable, 3000);
    }
    public void startNumberProgress(){

        new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                startAnimator(randomNumberProgress,15000);
            }

            public void onFinish() {
                sum  = firstNumber + secondNumber;
                firstNumber = secondNumber;
            }
        }.start();
    }
    public void startAnimator(ProgressBar progressBar,int duration){
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), 700);
        progressAnimator.setDuration(duration);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }
    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    private void  instantiateTextToSpeech(){
       mTextToSpeech =new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != android.speech.tts.TextToSpeech.ERROR) {
                  mTextToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }


}
