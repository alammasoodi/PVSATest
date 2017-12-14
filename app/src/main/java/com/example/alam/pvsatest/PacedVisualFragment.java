package com.example.alam.pvsatest;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;

/**
 * Created by alam on 14/12/17.
 */

public class PacedVisualFragment extends Fragment {
    View v;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_paced_visual, container, false);
        progressBar  = (ProgressBar) v.findViewById(R.id.progress_bar);
        progressBar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.buttonColor), android.graphics.PorterDuff.Mode.SRC_IN);
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", progressBar.getProgress(), 700);
                progressAnimator.setDuration(5000);
                progressAnimator.setInterpolator(new LinearInterpolator());
                progressAnimator.start();
            }

            @Override
            public void onFinish() {

            }

        }.start();
        return v;
    }

}
