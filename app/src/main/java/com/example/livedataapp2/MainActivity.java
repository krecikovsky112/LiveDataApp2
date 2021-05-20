package com.example.livedataapp2;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private TextView numberText;
    private EditText inputNumberText;
    private Button start;
    private Button stop;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setComponents();

        final MainActivityViewModel model = new ViewModelProvider(this).get(MainActivityViewModel.class);;
        final MutableLiveData<Integer> mySeconds = model.getSeconds();
        mySeconds.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                numberText.setText(integer.toString());
            }
        });
        final MutableLiveData<Boolean> myFinished = model.getFinished();
        myFinished.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Log.i(TAG, "Finished");
                    Toast.makeText(getApplicationContext(),"Finished!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MutableLiveData<Long> myTimerValue = model.getTimerValue();
                myTimerValue.setValue(Long.parseLong(String.valueOf(inputNumberText.getText())));
                model.startTimer();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.stopTimer();
            }
        });
    }

    private void setComponents() {
        numberText = findViewById(R.id.numberText);
        inputNumberText = findViewById(R.id.numberInput);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
    }
}