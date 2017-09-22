package com.vikoo.calendar.screens.calendarview;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vikoo.calendar.R;
import com.vikoo.calendar.utility.Utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by vikoo on 22/09/17.
 */
@RunWith(AndroidJUnit4.class)
public class CalendarActivityTest {

    @Rule
    public ActivityTestRule<CalendarActivity> mActivityRule
            = new ActivityTestRule<>(CalendarActivity.class);

    @Test
    public void setMonthText() throws Exception {
        Date date = new Date();
        Espresso.onView(ViewMatchers.withId(R.id.tv_month_and_year))
                .check(matches(ViewMatchers.withText(Utils.getCalenderMonth(date))));
    }

    @Test
    public void dateClick() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.calendar_view)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText(R.string.title_agenda)).check(matches(ViewMatchers.isDisplayed()));
    }

}