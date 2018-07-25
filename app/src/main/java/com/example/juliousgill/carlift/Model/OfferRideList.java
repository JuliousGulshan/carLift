package com.example.juliousgill.carlift.Model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.juliousgill.carlift.R;

import java.util.List;

/**
 * Created by Julious Gulshan - 15969
 */

public class OfferRideList extends ArrayAdapter<OfferRide> {

    private Activity context;
    private List<OfferRide> offerRideList;

    public OfferRideList(Activity context, List<OfferRide> offerRideList){

        super(context, R.layout.row_layout, offerRideList);
        this.context = context;
        this.offerRideList = offerRideList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

          View listViewItem = inflater.inflate(R.layout.row_layout, null, true);


        TextView tx_origin = (TextView) listViewItem.findViewById(R.id.tx_origin);
        TextView tx_destination = (TextView) listViewItem.findViewById(R.id.tx_destination);
        TextView tx_date_time = (TextView) listViewItem.findViewById(R.id.tx_date_time);
        TextView tx_phone = (TextView) listViewItem.findViewById(R.id.tx_phone);
        TextView tx_car = (TextView) listViewItem.findViewById(R.id.tx_car);


        OfferRide offerRide = offerRideList.get(position);

        tx_origin.setText(offerRide.getOfferSourceTxt());
        tx_destination.setText(offerRide.getOfferDestinationTxt());
        tx_date_time.setText(offerRide.getOfferTimeTxt());
        tx_phone.setText(offerRide.getOfferPhoneTxt());
        tx_car.setText(offerRide.getOfferCarTxt());

        return listViewItem;

    }

    public static void remove(int position) {
    }
}
