package com.example.donation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Donate extends AppCompatActivity {

    private Button donateButton;
    private RadioGroup paymentMethod;
    private NumberPicker amountPicker;
    private ProgressBar progressBar;

    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        donateButton = findViewById(R.id.donateButton);
        paymentMethod =  findViewById(R.id.paymentMethod);
        progressBar = findViewById(R.id.progressBar);
        amountPicker = findViewById(R.id.amountPicker);
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        progressBar.setMax(10000);
        total = 0;
        if (donateButton != null) {
            Log.v("Donate", "Really got the donate button");
        }
    }

    public void donateButtonPressed(View view) {
        int amount = amountPicker.getValue();
        total += amount;
        progressBar.setProgress(total);
        int radioId = paymentMethod.getCheckedRadioButtonId();
        String method = radioId == R.id.paypalButton ? "PayPal" : "Direct";
        Log.v("Donate", "Donate " + amount + " with method " + method + ". Current total : " + total);
    }
}