package com.vikoo.calendar.screens.agendaview;

import com.vikoo.calendar.base.BasePresenter;
import com.vikoo.calendar.base.BaseView;
import com.vikoo.calendar.screens.agendaview.models.Agenda;

import java.util.Date;
import java.util.List;

/**
 * Created by vikoo on 17/09/17.
 */

public class AgendaViewContract {

    public interface View extends BaseView<Presenter>{

        void showEvents(List<Agenda> events);
        List<Agenda> getCurrentAgendaList();

    }

    public interface Presenter extends BasePresenter<View>{

        void fetchEvents(Date date);
    }
}
