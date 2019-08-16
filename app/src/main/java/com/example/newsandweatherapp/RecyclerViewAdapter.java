package com.example.newsandweatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> weekDays = new ArrayList<>();

    private ArrayList<Drawable> icons = new ArrayList<>();
    private ArrayList<String> minTemperature = new ArrayList<>();
    private ArrayList<String> maxTemperature = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> weekDays, ArrayList<Drawable> icons, ArrayList<String> minTemperature, ArrayList<String> maxTemperature, Context mContext) {
        this.weekDays = weekDays;
        this.icons = icons;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.i("OnBindViewHolder:", "called.");
        viewHolder.futureWeatherIcon.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.wind));
        viewHolder.maxTemp.setText("Max: 27°C");
        viewHolder.minTemp.setText("Min: 19°C");
        viewHolder.weekDay.setText("Thursday");
        viewHolder.listItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item touched", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return weekDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView weekDay;
        public ImageView futureWeatherIcon;
        public TextView minTemp;
        public TextView maxTemp;
        public RelativeLayout listItemLayout;
        public LinearLayout listSubLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekDay = itemView.findViewById(R.id.weekDays);
            futureWeatherIcon = itemView.findViewById(R.id.futureWeatherIcon);
            minTemp = itemView.findViewById(R.id.minTemp);
            maxTemp = itemView.findViewById(R.id.maxTemp);
            listItemLayout = itemView.findViewById(R.id.listItemLayout);
            listSubLayout = itemView.findViewById(R.id.listSubLayout);
        }
    }
}
