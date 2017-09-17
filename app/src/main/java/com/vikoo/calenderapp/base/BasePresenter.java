package com.vikoo.calenderapp.base;

/**
 * Created by vikoo on 16/09/17.
 */

public interface BasePresenter<T> {

    void attach(T view);
    void detach();

}
