package com.vikoo.calendar.screens.agendaview;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.vikoo.calendar.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

/**
 * Created by vikoo on 22/09/17.
 */
public class AgendaActivityTest {

    @Rule
    public ActivityTestRule<AgendaActivity> mActivityRule
            = new ActivityTestRule<>(AgendaActivity.class, true, false);

    public void startActivityWithParams(Date date){
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, AgendaActivity.class);
        if(date != null){
            intent.putExtra(AgendaActivity.EXTRA_DATE, date);
        }
        mActivityRule.launchActivity(intent);
    }

    @Test
    public void launchActivity() throws Exception {
        startActivityWithParams(new Date());
        onView(ViewMatchers.withId(R.id.agenda_view)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void launchAddActivity() throws Exception {
        startActivityWithParams(new Date());
        onView(ViewMatchers.withId(R.id.btn_add_event)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.input_layout_date)).check(matches(ViewMatchers.isDisplayed()));
    }

}