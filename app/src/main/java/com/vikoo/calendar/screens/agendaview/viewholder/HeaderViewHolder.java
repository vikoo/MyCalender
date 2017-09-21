package com.vikoo.calendar.screens.agendaview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vikoo.calendar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikoo on 17/09/17.
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.group_date)
    public TextView tvDate;

    @BindView(R.id.header_layout)
    public LinearLayout headerLayout;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvDate = itemView.findViewById(R.id.group_date);
    }
}
