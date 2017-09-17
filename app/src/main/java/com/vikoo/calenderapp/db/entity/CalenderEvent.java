package com.vikoo.calenderapp.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.vikoo.calenderapp.base.BaseEvent;

/**
 * Created by vikoo on 16/09/17.
 */

@Entity
public class CalenderEvent extends BaseEvent implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int eventId;
    public int eventColor;
    public long time;
    public boolean isAllDayEvent;
    public String eventTitle;
    public String eventDescription;
    public long duration;
    public double latitude;
    public double longitude;
    public String location;

    public CalenderEvent(){}

    protected CalenderEvent(Parcel in) {
        eventId = in.readInt();
        eventColor = in.readInt();
        time = in.readLong();
        isAllDayEvent = in.readByte() != 0;
        eventTitle = in.readString();
        eventDescription = in.readString();
        duration = in.readLong();
        latitude = in.readDouble();
        longitude = in.readDouble();
        location = in.readString();
    }

    public static final Creator<CalenderEvent> CREATOR = new Creator<CalenderEvent>() {
        @Override
        public CalenderEvent createFromParcel(Parcel in) {
            return new CalenderEvent(in);
        }

        @Override
        public CalenderEvent[] newArray(int size) {
            return new CalenderEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(eventId);
        parcel.writeInt(eventColor);
        parcel.writeLong(time);
        parcel.writeByte((byte) (isAllDayEvent ? 1 : 0));
        parcel.writeString(eventTitle);
        parcel.writeString(eventDescription);
        parcel.writeLong(duration);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(location);
    }
}
