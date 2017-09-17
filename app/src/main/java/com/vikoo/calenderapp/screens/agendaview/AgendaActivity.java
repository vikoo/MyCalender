package com.vikoo.calenderapp.screens.agendaview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.vikoo.calenderapp.CalenderApplication;
import com.vikoo.calenderapp.R;
import com.vikoo.calenderapp.di.component.ApplicationComponent;
import com.vikoo.calenderapp.di.component.DaggerActivityComponent;
import com.vikoo.calenderapp.screens.agendaview.models.Agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikoo on 17/09/17.
 */

public class AgendaActivity extends AppCompatActivity implements AgendaViewContract.View{

    private static final String EXTRA_DATE = "date";

    @Inject
    AgendaViewContract.Presenter mPresenter;

    @BindView(R.id.agenda_view)
    RecyclerView mAgendaView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private AgendaAdapter mAdapter;
    private List<Agenda> mAgendaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DaggerActivityComponent.builder()
                .applicationComponent(CalenderApplication.get(this).getApplicationComponent())
                .build()
                .inject(this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAgendaList = new ArrayList<>();
        mAdapter = new AgendaAdapter(mAgendaList, getApplicationContext());
        mAgendaView.setLayoutManager(layoutManager);
        mAgendaView.setAdapter(mAdapter);
        mPresenter.attach(this);
        Date date = (Date) getIntent().getExtras().getSerializable(EXTRA_DATE);
        mPresenter.fetchEvents(date);
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

    @Override
    public ApplicationComponent getApplicationComponent() {
        return CalenderApplication.get(this).getApplicationComponent();
    }

}
