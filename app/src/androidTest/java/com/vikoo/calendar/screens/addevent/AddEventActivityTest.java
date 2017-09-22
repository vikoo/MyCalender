package com.vikoo.calendar.screens.addevent;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.vikoo.calendar.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by vikoo on 22/09/17.
 */
public class AddEventActivityTest {

    @Rule
    public ActivityTestRule<AddEventActivity> activityRule
            = new ActivityTestRule<>(AddEventActivity.class);

    private void loadUi(String eventName, String date, String time, String duration, boolean isAllDay){
        onView(withId(R.id.input_event_name)).perform(replaceText(eventName));
        onView(withId(R.id.input_date)).perform(replaceText(date));
        onView(withId(R.id.input_time)).perform(replaceText(time));
        onView(withId(R.id.input_duration)).perform(replaceText(duration));
        if(isAllDay) {
            onView(withId(R.id.chk_all_day)).perform(ViewActions.click());
        }
    }

    @Test
    public void launchActivity() throws Exception {
        onView(withId(R.id.input_date)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testError_eventNameNull() throws Exception {
        loadUi("", "23/09/2017", "08:30", "1", false);
        onView(withId(R.id.btn_add_event)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText(R.string.error_event_name)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testError_dateNull() throws Exception {
        loadUi("Test Event", "", "08:30", "1", false);
        onView(withId(R.id.btn_add_event)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText(R.string.error_event_date)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testError_timeNull() throws Exception {
        loadUi("Test Event", "23/09/2017", "", "1", false);
        onView(withId(R.id.btn_add_event)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText(R.string.error_event_time)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testError_durationNull() throws Exception {
        loadUi("Test Event", "23/09/2017", "08:30", "", false);
        onView(withId(R.id.btn_add_event)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withText(R.string.error_event_duration)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testInput_isAllDayCheck() throws Exception {
        loadUi("Test Event", "23/09/2017", "08:30", "1", true);
        onView(withId(R.id.input_duration)).check(matches(not(isEnabled())));
    }

    @Test
    public void testInput_isNotAllDayCheck() throws Exception {
        loadUi("Test Event", "23/09/2017", "08:30", "1", false);
        onView(withId(R.id.input_duration)).check(matches(isEnabled()));
    }

}