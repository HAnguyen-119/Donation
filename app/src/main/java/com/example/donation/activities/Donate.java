package com.example.donation.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.donation.R;
import com.example.donation.models.Donation;

public class Donate extends Base {

    private Button donateButton;
    private RadioGroup paymentMethod;
    private NumberPicker amountPicker;
    private ProgressBar progressBar;
    private TextView amount;
    private TextView totalSoFar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.donate_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        progressBar.setMax(target);
        progressBar.setProgress(totalDonated);

        amount = findViewById(R.id.amount);
        amountPicker.setOnValueChangedListener((picker, oldVal, newVal) -> amount.setText("$" + amountPicker.getValue()));
        totalSoFar = findViewById(R.id.totalSoFar);
        totalSoFar.setText("$" + totalDonated);
        if (donateButton != null) {
            Log.v("Donate", "Really got the donate button");
        }
    }

    public void donateButtonPressed(View view) {
        int amount = amountPicker.getValue();
        int radioId = paymentMethod.getCheckedRadioButtonId();
        String method = radioId == R.id.paypalButton ? "PayPal" : "Direct";
        Donation donation = new Donation(amount, method);
        newDonation(donation);
        totalSoFar.setText("$" + totalDonated);
        progressBar.setProgress(totalDonated);

        Log.v("Donate", "Donate " + amount + " with method " + method + ". Current total : " + totalDonated);
    }
}