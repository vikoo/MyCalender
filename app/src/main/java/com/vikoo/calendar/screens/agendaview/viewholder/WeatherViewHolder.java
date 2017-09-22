package com.vikoo.calendar.screens.agendaview.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikoo.calendar.R;
import com.vikoo.calendar.screens.agendaview.models.WeatherEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikoo on 17/09/17.
 */

public class WeatherViewHolder extends ChildViewHolder {

    @BindView(R.id.weatherTime)
    public TextView mTvWeatherTime;
    @BindView(R.id.temperature)
    public TextView mTvTemperature;
    @BindView(R.id.weatherImage)
    public ImageView mIvWeatherImage;

    public WeatherViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setView(WeatherEvent event) {
        mTvTemperature.setText(event.temperature + "°F");
        mTvWeatherTime.setText(event.eventName);
    }
}
