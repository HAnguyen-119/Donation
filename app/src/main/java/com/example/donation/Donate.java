package com.example.donation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Donate extends AppCompatActivity {

    private Button donateButton;
    private RadioGroup paymentMethod;
    private NumberPicker amountPicker;
    private ProgressBar progressBar;
    private TextView amount;
    private TextView totalSoFar;


    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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
        progressBar.setMax(10000);

        amount = findViewById(R.id.amount);
        amountPicker.setOnValueChangedListener((picker, oldVal, newVal) -> amount.setText("$" + amountPicker.getValue()));
        totalSoFar = findViewById(R.id.totalSoFar);
        total = 0;
        if (donateButton != null) {
            Log.v("Donate", "Really got the donate button");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_report) {
            startActivity (new Intent(this, Report.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void donateButtonPressed(View view) {
        int amount = amountPicker.getValue();
        total += amount;
        totalSoFar.setText("$" + total);
        progressBar.setProgress(total);
        int radioId = paymentMethod.getCheckedRadioButtonId();
        String method = radioId == R.id.paypalButton ? "PayPal" : "Direct";
        Log.v("Donate", "Donate " + amount + " with method " + method + ". Current total : " + total);
    }
}