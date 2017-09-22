package com.vikoo.calendar.screens.agendaview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.vikoo.calendar.R;
import com.vikoo.calendar.base.BaseEvent;
import com.vikoo.calendar.db.entity.CalenderEvent;
import com.vikoo.calendar.screens.agendaview.models.Agenda;
import com.vikoo.calendar.screens.agendaview.models.NoEvent;
import com.vikoo.calendar.screens.agendaview.models.WeatherEvent;
import com.vikoo.calendar.screens.agendaview.viewholder.ChildViewHolder;
import com.vikoo.calendar.screens.agendaview.viewholder.EventViewHolder;
import com.vikoo.calendar.screens.agendaview.viewholder.FooterViewHolder;
import com.vikoo.calendar.screens.agendaview.viewholder.HeaderViewHolder;
import com.vikoo.calendar.screens.agendaview.viewholder.NoItemViewHolder;
import com.vikoo.calendar.screens.agendaview.viewholder.WeatherViewHolder;
import com.vikoo.calendar.utility.Utils;

import java.util.List;

import static android.view.LayoutInflater.from;

/**
 * Created by vikoo on 17/09/17.
 */

public class AgendaAdapter extends SectionedRecyclerViewAdapter<HeaderViewHolder, ChildViewHolder, FooterViewHolder> {

    private static final int TYPE_NO_ITEM = 1;
    private static final int TYPE_EVENT = 2;
    private static final int TYPE_WEATHER = 3;

    private List<Agenda> mAgendaList;
    private Context mContext;

    public List<Agenda> getAgendaList() {
        return mAgendaList;
    }

    public void setAgendaList(List<Agenda> mAgendaList) {
        this.mAgendaList = mAgendaList;
        notifyDataSetChanged();
    }

    public AgendaAdapter(List<Agenda> list, Context context){
        mAgendaList = list;
        mContext = context;
    }

    @Override
    protected int getSectionItemViewType(int section, int position) {
        BaseEvent event = mAgendaList.get(section).events.get(position);
        if (event instanceof CalenderEvent) {
            return TYPE_EVENT;
        } else if (event instanceof NoEvent) {
            return TYPE_NO_ITEM;
        } else {
            return TYPE_WEATHER;
        }
    }

    protected BaseEvent getEvent(int section, int position) {
        return mAgendaList.get(section).events.get(position);
    }

    @Override
    protected int getSectionCount() {
        return mAgendaList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return mAgendaList.get(section).events.size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_view, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    protected FooterViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected ChildViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_EVENT:
                View view = from(parent.getContext()).inflate(R.layout.item_child_calender_event, parent, false);
                return new EventViewHolder(view);
            default:
            case TYPE_NO_ITEM:
                view = from(parent.getContext()).inflate(R.layout.item_child_no_event, parent, false);
                return new NoItemViewHolder(view);
            case TYPE_WEATHER:
                view = from(parent.getContext()).inflate(R.layout.item_child_weather, parent, false);
                return new WeatherViewHolder(view);
        }
    }

    @Override
    protected void onBindSectionHeaderViewHolder(HeaderViewHolder holder, int section) {
        Agenda agenda = mAgendaList.get(section);
        if(Utils.isToday(agenda.dayId)){
            holder.mHeaderLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorHeaderBlue));
            holder.mTvDate.setTextColor(ContextCompat.getColor(mContext,R.color.colorDividerBlue));
        } else{
            holder.mHeaderLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorHeaderGrey));
            holder.mTvDate.setTextColor(ContextCompat.getColor(mContext,R.color.colorDividerGrey));
        }
        holder.mTvDate.setText(agenda.title);
    }

    @Override
    protected void onBindSectionFooterViewHolder(FooterViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(ChildViewHolder holder, int section, int position) {
        int viewType = getSectionItemViewType(section, position);
        BaseEvent event = getEvent(section, position);
        switch (viewType) {
            case TYPE_EVENT:
                CalenderEvent calenderEvent = ((CalenderEvent) event);
                ((EventViewHolder)holder).setView(calenderEvent);
                break;
            case TYPE_WEATHER:
                WeatherEvent weatherEvent = ((WeatherEvent) event);
                ((WeatherViewHolder)holder).setView(weatherEvent);
                break;
        }
    }
}
