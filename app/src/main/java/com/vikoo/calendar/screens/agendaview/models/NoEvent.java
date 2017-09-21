package com.vikoo.calendar.screens.agendaview.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.vikoo.calendar.base.BaseEvent;

/**
 * Created by vikoo on 17/09/17.
 */

public class NoEvent extends BaseEvent implements Parcelable{

    protected NoEvent(Parcel in) {
    }
    public NoEvent(){

    }

    public static final Creator<NoEvent> CREATOR = new Creator<NoEvent>() {
        @Override
        public NoEvent createFromParcel(Parcel in) {
            return new NoEvent(in);
        }

        @Override
        public NoEvent[] newArray(int size) {
            return new NoEvent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
