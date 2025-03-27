package com.example.donation.activities;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.example.donation.R;
import com.example.donation.models.DonationAdapter;

public class Report extends Base {
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.reportList);
        DonationAdapter adapter = new DonationAdapter(this, donations);
        listView.setAdapter(adapter);
    }
}
