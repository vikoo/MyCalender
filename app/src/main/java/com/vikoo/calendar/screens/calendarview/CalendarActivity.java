package com.vikoo.calendar.screens.calendarview;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.vikoo.calendar.CalendarApplication;
import com.vikoo.calendar.R;
import com.vikoo.calendar.base.BaseActivity;
import com.vikoo.calendar.di.component.DaggerActivityComponent;
import com.vikoo.calendar.screens.agendaview.AgendaActivity;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by vikoo on 17/09/17.
 */

public class CalendarActivity extends BaseActivity implements CalendarViewContract.View{

    @BindView(R.id.calendar_view)
    CompactCalendarView mCalendarView;

    @BindView(R.id.tv_month_and_year)
    TextView mTvMonthYear;

    @Inject
    CalendarViewContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);

        setupToolBar();
        DaggerActivityComponent.builder()
                .applicationComponent(CalendarApplication.get(this).getApplicationComponent())
                .build()
                .inject(this);

        mPresenter.attach(this, mApplicationComponent);

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
        String deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Timber.i("UUID:" + deviceId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCalendarView.removeAllEvents();
        mPresenter.fetchEvents();
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

}
