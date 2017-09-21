package com.vikoo.calendar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vikoo.calendar.CalendarApplication;
import com.vikoo.calendar.R;
import com.vikoo.calendar.di.component.ApplicationComponent;

import butterknife.BindView;

/**
 * Created by vekariya.r on 19/09/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    public ApplicationComponent mApplicationComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplicationComponent = CalendarApplication.get(this).getApplicationComponent();
    }

    public void setupToolBar(){
        setSupportActionBar(mToolbar);
    }

    public void setupToolBarWithBack(){
        setupToolBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
