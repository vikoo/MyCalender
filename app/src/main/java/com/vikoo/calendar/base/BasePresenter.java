package com.vikoo.calendar.base;

import com.vikoo.calendar.di.component.ApplicationComponent;

/**
 * Created by vikoo on 16/09/17.
 */

public interface BasePresenter<T> {

    void attach(T view, ApplicationComponent applicationComponent);
    void detach();

}
