package com.example.livedataapp2;

import android.os.CountDownTimer;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private final String TAG = this.getClass().getSimpleName();
    private CountDownTimer timer;
    private MutableLiveData<Boolean> finished;

    public MutableLiveData<Long> getTimerValue() {
        Log.i(TAG, "Get timerValue");
        if(timerValue == null){
            timerValue = new MutableLiveData<>();
        }
        return timerValue;
    }

    private MutableLiveData<Long> timerValue;

    private MutableLiveData<Integer> seconds;

    public MutableLiveData<Integer> getSeconds(){
        Log.i(TAG, "Get seconds");

        if(seconds == null){
            seconds = new MutableLiveData<>();
        }

        return seconds;
    }

    public MutableLiveData<Boolean> getFinished(){
        Log.i(TAG, "Get finished flag");

        if(finished == null){
            finished = new MutableLiveData<>();
        }

        return finished;
    }

    public void startTimer(){
        timer = new CountDownTimer(timerValue.getValue()*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft = millisUntilFinished/1000;
                Log.i(TAG, String.valueOf(timeLeft));
                seconds.setValue((int) timeLeft);
            }

            @Override
            public void onFinish() {
                finished.setValue(true);
                Log.i(TAG, "Finished!");
            }
        }.start();
    }

    public void stopTimer(){
        timer.cancel();
        Log.i(TAG, "Stop timer!");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

}
