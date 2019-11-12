package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


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
        //
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        TextView locationView = (TextView) listItemView.findViewById(R.id.place);
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        magnitudeView.setText(currentEarthquale.getmMagnitude());
        locationView.setText(currentEarthquale.getmLocation());
        dateView.setText(currentEarthquale.getmDate());

        return listItemView;

    }
}
