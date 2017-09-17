package com.vikoo.calenderapp.screens.agendaview.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikoo.calenderapp.R;
import com.vikoo.calenderapp.db.entity.CalenderEvent;
import com.vikoo.calenderapp.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikoo on 17/09/17.
 */

public class EventViewHolder extends ChildViewHolder {

    @BindView(R.id.time)
    public TextView tvTime;
    @BindView(R.id.duration)
    public TextView tvDuration;
    @BindView(R.id.event)
    public TextView tvEventName;
    @BindView(R.id.location)
    public TextView tvLocation;
    @BindView(R.id.event_color)
    public ImageView ivEventColor;

    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setView(CalenderEvent event){
        tvTime.setText(Utils.getTimeInAMPM(event.time));
        if(event.isAllDayEvent){
            tvDuration.setText(R.string.all_day);
        }else {
            tvDuration.setText(Utils.getHours(event.duration));
        }
        tvEventName.setText(event.eventTitle);
        if(TextUtils.isEmpty(event.location)){
            tvLocation.setVisibility(View.INVISIBLE);
        } else{
            tvLocation.setVisibility(View.VISIBLE);
            tvLocation.setText(event.location);
        }
    }
}
