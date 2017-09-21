package com.vikoo.calendar.screens.agendaview.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.vikoo.calendar.base.BaseEvent;

/**
 * Created by vikoo on 17/09/17.
 */

public class WeatherEvent extends BaseEvent implements Parcelable{
    public String eventName;
    public String weatherSummary;
    public long time;
    public String temperature;

    public WeatherEvent(){

    }
    protected WeatherEvent(Parcel in) {
        eventName = in.readString();
        weatherSummary = in.readString();
        time = in.readLong();
        temperature = in.readString();
    }

    public static final Creator<WeatherEvent> CREATOR = new Creator<WeatherEvent>() {
        @Override
        public WeatherEvent createFromParcel(Parcel in) {
            return new WeatherEvent(in);
        }

        @Override
        public WeatherEvent[] newArray(int size) {
            return new WeatherEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(eventName);
        parcel.writeString(weatherSummary);
        parcel.writeLong(time);
        parcel.writeString(temperature);
    }
}
