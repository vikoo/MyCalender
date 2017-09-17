package com.vikoo.calenderapp.screens.calenderview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.vikoo.calenderapp.CalenderApplication;
import com.vikoo.calenderapp.R;
import com.vikoo.calenderapp.screens.agendaview.AgendaActivity;
import com.vikoo.calenderapp.db.entity.CalenderEvent;
import com.vikoo.calenderapp.di.component.ApplicationComponent;
import com.vikoo.calenderapp.di.component.DaggerActivityComponent;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikoo on 17/09/17.
 */

public class CalenderActivity extends AppCompatActivity implements CalenderViewContract.View{

    @BindView(R.id.calender_view)
    CompactCalendarView mCalendarView;

    @BindView(R.id.tv_month_and_year)
    TextView mTvMonthYear;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    CalenderViewContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        DaggerActivityComponent.builder()
                .applicationComponent(CalenderApplication.get(this).getApplicationComponent())
                .build()
                .inject(this);

        mPresenter.attach(this);

        mCalendarView.setShouldDrawDaysHeader(true);
        mCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                mPresenter.onDayClick(dateClicked);
                startActivity(AgendaActivity.getIntent(getApplicationContext(), dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mPresenter.onMonthScroll(firstDayOfNewMonth);
            }
        });

        mCalendarView.setUseThreeLetterAbbreviation(true);

    }


    @Override
    public void showEvents(List<Event> events) {
        if(events != null){
            for(Event event : events){
                mCalendarView.addEvent(event, true);
            }
        }
    }

    @Override
    public void setMonthText(String text) {
        mTvMonthYear.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    @Override
    public ApplicationComponent getApplicationComponent() {
        return CalenderApplication.get(this).getApplicationComponent();
    }
}
