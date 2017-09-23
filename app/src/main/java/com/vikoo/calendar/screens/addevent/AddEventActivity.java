package com.vikoo.calendar.screens.addevent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.vikoo.calendar.CalendarApplication;
import com.vikoo.calendar.R;
import com.vikoo.calendar.base.BaseActivity;
import com.vikoo.calendar.di.component.DaggerActivityComponent;
import com.vikoo.calendar.utility.Utils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vikoo on 17/09/17.
 */

public class AddEventActivity extends BaseActivity implements AddEventContract.View{

    @Inject
    AddEventContract.Presenter mPresenter;

    @BindView(R.id.input_layout_event_name)
    TextInputLayout mIlName;

    @BindView(R.id.input_event_name)
    EditText mEtName;

    @BindView(R.id.input_layout_date)
    TextInputLayout mIlDate;

    @BindView(R.id.input_date)
    EditText mEtDate;

    @BindView(R.id.input_layout_time)
    TextInputLayout mIlTime;

    @BindView(R.id.input_time)
    EditText mEtTime;

    @BindView(R.id.input_layout_duration)
    TextInputLayout mIlDuration;

    @BindView(R.id.input_duration)
    EditText mEtDuration;

    @BindView(R.id.chk_all_day)
    CheckBox mChkAllDay;

    private boolean mIsAllDay;
    private int mEventColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);

        setupToolBarWithBack();
        DaggerActivityComponent.builder()
                .applicationComponent(CalendarApplication.get(this).getApplicationComponent())
                .build()
                .inject(this);
        mPresenter.attach(this, mApplicationComponent);

        mChkAllDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mIsAllDay = b;
                mEtDuration.setEnabled(!b);
                mEtDuration.setClickable(!b);
            }
        });
        mEventColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
    }

    @Override
    public void onValidationFailed(int errorCode) {
        switch (errorCode){
            case AddEventPresenter.ERROR_NAME:
                mIlName.setError(getResources().getString(R.string.error_event_name));
                break;
            case AddEventPresenter.ERROR_DATE:
                mIlDate.setError(getResources().getString(R.string.error_event_date));
                break;
            case AddEventPresenter.ERROR_DURATION:
                mIlDuration.setError(getResources().getString(R.string.error_event_duration));
                break;
            case AddEventPresenter.ERROR_TIME:
                mIlTime.setError(getResources().getString(R.string.error_event_time));
                break;
        }
    }

    private void clearErrors(){
        mIlName.setError("");
        mIlDate.setError("");
        mIlDuration.setError("");
    }

    @Override
    public void onEventAdded() {
        Toast.makeText(getApplicationContext(), R.string.event_add_success, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick({R.id.input_layout_date, R.id.input_date})
    public void onDateClick(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this,myDateListener, year, month, day);
        dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int year, int month, int day) {
                    showDate(year, month+1, day);
                }
            };

    private void showDate(int year, int month, int day){
        mEtDate.setText(new StringBuilder().append(Utils.formatInTwoDigit(day)).append("/")
                .append(Utils.formatInTwoDigit(month)).append("/").append(year));
    }

    @OnClick({R.id.input_layout_time, R.id.input_time})
    public void onTimeClick(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        new TimePickerDialog(this, timeSetListener, hour, minute,
                DateFormat.is24HourFormat(this)).show();
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            showtime(hour,minute);
        }
    };


    private void showtime(int hour, int minute){
        mEtTime.setText(new StringBuilder().append(Utils.formatInTwoDigit(hour)).append(":")
                .append(Utils.formatInTwoDigit(minute)));
    }

    @OnClick({R.id.btn_add_event})
    public void onAddEventClick(View view){
        clearErrors();
        mPresenter.validate(mEtName.getText().toString(),
                mEtDate.getText().toString(),
                mEtTime.getText().toString(),
                mEtDuration.getText().toString(),
                mIsAllDay,
                mEventColor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
