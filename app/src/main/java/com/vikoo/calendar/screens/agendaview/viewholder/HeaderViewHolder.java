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
    public TextView mTvDate;

    @BindView(R.id.header_layout)
    public LinearLayout mHeaderLayout;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mTvDate = itemView.findViewById(R.id.group_date);
    }
}
