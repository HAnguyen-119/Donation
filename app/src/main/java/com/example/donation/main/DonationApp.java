package com.example.donation.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.donation.database.DBManager;
import com.example.donation.models.Donation;


public class DonationApp extends Application {
    public final int target = 10000;
    public static int totalDonated = 0;
//    public static List<Donation> donations = new ArrayList<>();

    public DBManager dbManager;

    public boolean newDonation(Donation donation) {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved) {
            dbManager.add(donation);
            totalDonated += donation.amount;
        } else {
            Toast toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT);
            toast.show();
        }
        return targetAchieved;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Donate", "Donation app started");
        dbManager = new DBManager(this);
    }
}
