package com.vikoo.calendar.screens.agendaview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vikoo.calendar.CalendarApplication;
import com.vikoo.calendar.R;
import com.vikoo.calendar.base.BaseActivity;
import com.vikoo.calendar.di.component.DaggerActivityComponent;
import com.vikoo.calendar.screens.addevent.AddEventActivity;
import com.vikoo.calendar.screens.agendaview.models.Agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vikoo on 17/09/17.
 */

public class AgendaActivity extends BaseActivity implements AgendaViewContract.View{

    private static final String EXTRA_DATE = "date";

    @Inject
    AgendaViewContract.Presenter mPresenter;

    @BindView(R.id.agenda_view)
    RecyclerView mAgendaView;

    private AgendaAdapter mAdapter;
    private List<Agenda> mAgendaList;
    private Date mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        ButterKnife.bind(this);

        setupToolBarWithBack();
        DaggerActivityComponent.builder()
                .applicationComponent(CalendarApplication.get(this).getApplicationComponent())
                .build()
                .inject(this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAgendaList = new ArrayList<>();
        mAdapter = new AgendaAdapter(mAgendaList, getApplicationContext());
        mAgendaView.setLayoutManager(layoutManager);
        mAgendaView.setAdapter(mAdapter);
        mPresenter.attach(this, mApplicationComponent);
        mDate = (Date) getIntent().getExtras().getSerializable(EXTRA_DATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.fetchEvents(mDate);
    }

    public static Intent getIntent(Context context, Date date){
        Intent intent = new Intent(context, AgendaActivity.class);
        intent.putExtra(EXTRA_DATE, date);
        return intent;
    }

    @Override
    public void showEvents(List<Agenda> events) {
        mAgendaList.clear();
        mAgendaList = events;
        mAdapter.setAgendaList(events);
    }

    @Override
    public List<Agenda> getCurrentAgendaList() {
        return mAgendaList;
    }

    @OnClick(R.id.btn_add_event)
    public void onAddEventClick(View view){
        Intent i = new Intent(getApplicationContext(), AddEventActivity.class);
        startActivity(i);
    }

}
