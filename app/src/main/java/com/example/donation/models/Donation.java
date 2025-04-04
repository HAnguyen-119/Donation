package com.example.donation.models;

import androidx.annotation.NonNull;

public class Donation
{
    public int id;public int amount;
    public String method;
    public Donation (int amount, String method)
    {
        this.amount = amount;
        this.method = method;
    }
    public Donation ()
    {
        this.amount = 0;
        this.method = "";
    }

    @NonNull
    @Override
    public String toString()
    {
        return id + ", " + amount + ", " + method;
    }
}