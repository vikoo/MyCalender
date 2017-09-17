package com.vikoo.calenderapp.screens.agendaview;

import com.vikoo.calenderapp.base.BasePresenter;
import com.vikoo.calenderapp.base.BaseView;
import com.vikoo.calenderapp.screens.agendaview.models.Agenda;

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
