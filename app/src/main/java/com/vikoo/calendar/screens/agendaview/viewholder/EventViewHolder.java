package com.vikoo.calendar.screens.agendaview.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikoo.calendar.R;
import com.vikoo.calendar.db.entity.CalenderEvent;
import com.vikoo.calendar.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikoo on 17/09/17.
 */

public class EventViewHolder extends ChildViewHolder {

    @BindView(R.id.time)
    public TextView mTvTime;
    @BindView(R.id.duration)
    public TextView mTvDuration;
    @BindView(R.id.event)
    public TextView mTvEventName;
    @BindView(R.id.location)
    public TextView mTvLocation;
    @BindView(R.id.event_color)
    public ImageView mIvEventColor;

    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setView(CalenderEvent event){
        mTvTime.setText(Utils.getTimeInAMPM(event.time));
        if(event.isAllDayEvent){
            mTvDuration.setText(R.string.all_day);
        }else {
            mTvDuration.setText(Utils.getHours(event.duration));
        }
        mTvEventName.setText(event.eventTitle);
        if(TextUtils.isEmpty(event.location)){
            mTvLocation.setVisibility(View.INVISIBLE);
        } else{
            mTvLocation.setVisibility(View.VISIBLE);
            mTvLocation.setText(event.location);
        }
    }
}
