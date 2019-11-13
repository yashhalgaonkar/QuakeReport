package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static  final String LOCATION_SEPARATOR = "of";
    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.earthquake_list_item, parent, false);
        }
        //current earthquake is one from the list...
        Earthquake currentEarthquale = getItem(position);


        //find the textViews
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        TextView locationOffsetView = (TextView)listItemView.findViewById(R.id.location_offset);
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);


        //splitting location into two parts
        String originalLocation = currentEarthquale.getmLocation();
        String primaryLocation;
        String locationOffset;
        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }else{
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


        //create a new date object
        Date date = new Date(currentEarthquale.getmTimeInMilliseconds());

        //getting the magnitude color for background circle
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquale.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        //setting the textView
        dateView.setText(formatDate(date));
        timeView.setText(formatTime(date));
        magnitudeView.setText(formatMagnitude(currentEarthquale.getmMagnitude()));
        primaryLocationView.setText(primaryLocation);
        locationOffsetView.setText(locationOffset);

        return listItemView;

    }

    /*
    Helper methods to format date and time
     */
    private String formatDate(Date date){
        return new SimpleDateFormat("LLL dd, yyyy").format(date);
    }
    private String formatTime(Date date){
        return new SimpleDateFormat("h:mm a").format(date);
    }
    private String formatMagnitude(Double magnitude){
        return new DecimalFormat("0.0").format(magnitude);
    }

    private int getMagnitudeColor(Double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch(magnitudeFloor){
            case 0:
                case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;

        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
