package com.vikoo.calenderapp.screens.agendaview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truizlop.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.vikoo.calenderapp.R;
import com.vikoo.calenderapp.base.BaseEvent;
import com.vikoo.calenderapp.db.entity.CalenderEvent;
import com.vikoo.calenderapp.screens.agendaview.models.Agenda;
import com.vikoo.calenderapp.screens.agendaview.models.NoEvent;
import com.vikoo.calenderapp.screens.agendaview.models.WeatherEvent;
import com.vikoo.calenderapp.screens.agendaview.viewholder.ChildViewHolder;
import com.vikoo.calenderapp.screens.agendaview.viewholder.EventViewHolder;
import com.vikoo.calenderapp.screens.agendaview.viewholder.FooterViewHolder;
import com.vikoo.calenderapp.screens.agendaview.viewholder.HeaderViewHolder;
import com.vikoo.calenderapp.screens.agendaview.viewholder.NoItemViewHolder;
import com.vikoo.calenderapp.screens.agendaview.viewholder.WeatherViewHolder;
import com.vikoo.calenderapp.utility.Utils;

import java.util.List;

import static android.view.LayoutInflater.from;

/**
 * Created by vikoo on 17/09/17.
 */

public class AgendaAdapter extends SectionedRecyclerViewAdapter<HeaderViewHolder, ChildViewHolder, FooterViewHolder> {

    private static final int TYPE_NO_ITEM = 1;
    private static final int TYPE_EVENT = 2;
    private static final int TYPE_WEATHER = 3;

    public List<Agenda> getAgendaList() {
        return agendaList;
    }

    public void setAgendaList(List<Agenda> agendaList) {
        this.agendaList = agendaList;
        notifyDataSetChanged();
    }

    private List<Agenda> agendaList;
    private Context mContext;

    public AgendaAdapter(List<Agenda> list, Context context){
        agendaList = list;
        mContext = context;
    }

    @Override
    protected int getSectionItemViewType(int section, int position) {
        BaseEvent event = agendaList.get(section).events.get(position);
        if (event instanceof CalenderEvent) {
            return TYPE_EVENT;
        } else if (event instanceof NoEvent) {
            return TYPE_NO_ITEM;
        } else {
            return TYPE_WEATHER;
        }
    }

    protected BaseEvent getEvent(int section, int position) {
        return agendaList.get(section).events.get(position);
    }

    @Override
    protected int getSectionCount() {
        return agendaList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        return agendaList.get(section).events.size();
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
        Agenda agenda = agendaList.get(section);
        if(Utils.isToday(agenda.dayId)){
            holder.headerLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorHeaderBlue));
            holder.tvDate.setTextColor(ContextCompat.getColor(mContext,R.color.colorDividerBlue));
        } else{
            holder.headerLayout.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorHeaderGrey));
            holder.tvDate.setTextColor(ContextCompat.getColor(mContext,R.color.colorDividerGrey));
        }
        holder.tvDate.setText(agenda.title);
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
