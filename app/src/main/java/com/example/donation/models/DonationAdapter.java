package com.example.donation.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.donation.R;

import java.util.List;

public class DonationAdapter extends ArrayAdapter<Donation>
{
    private Context context;
    public List<Donation> donations;
    public DonationAdapter(Context context, List<Donation> donations)
    {
        super(context, R.layout.row_donate, donations);
        this.context = context;
        this.donations = donations;
    } @Override

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_donate, parent, false);
        Donation donation = donations.get(position);
        TextView amountView = view.findViewById(R.id.row_amount);
        TextView methodView = view.findViewById(R.id.row_method);
        amountView.setText("$" + donation.amount);
        methodView.setText(donation.method);
        return view;
    }

    @Override
    public int getCount()
    {
        return donations.size();
    }
}
