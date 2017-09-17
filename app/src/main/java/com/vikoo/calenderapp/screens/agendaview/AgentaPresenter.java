package com.vikoo.calenderapp.screens.agendaview;

import com.vikoo.calenderapp.db.entity.CalenderEvent;
import com.vikoo.calenderapp.di.component.ApplicationComponent;
import com.vikoo.calenderapp.network.model.DailyData;
import com.vikoo.calenderapp.network.model.DailyWeather;
import com.vikoo.calenderapp.network.model.WeatherResponse;
import com.vikoo.calenderapp.screens.agendaview.models.Agenda;
import com.vikoo.calenderapp.screens.agendaview.models.NoEvent;
import com.vikoo.calenderapp.screens.agendaview.models.WeatherEvent;
import com.vikoo.calenderapp.utility.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by vikoo on 17/09/17.
 */

public class AgentaPresenter implements AgendaViewContract.Presenter {

    private AgendaViewContract.View mView;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void attach(AgendaViewContract.View view) {
        mView = view;
        mApplicationComponent = mView.getApplicationComponent();
    }

    @Override
    public void detach() {
        mView = null;
    }


    @Override
    public void fetchEvents(final Date date) {
        Timber.i("fetchEvents: " + date);
        final long startTime = Utils.getStartTime(date);
        final long endTime = Utils.getEndTime(date, 7);
        mApplicationComponent.getDatabase().getCalenderEventDao().getEntriesBetweenDate(startTime, endTime).subscribe(new SingleObserver<List<CalenderEvent>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<CalenderEvent> calenderEvents) {
                processEvents(calenderEvents, date);
                doNetwork(startTime, endTime);
            }

            @Override
            public void onError(Throwable e) {
                doNetwork(startTime, endTime);
            }
        });
    }

    private void processEvents(List<CalenderEvent> events, Date date){
        TreeMap<Long, Agenda> agendaMap = new TreeMap<>();
        long startTime = Utils.getStartTime(date);
        for(int i= 0 ; i< 7; i++){
            Agenda agenda = new Agenda();
            agenda.events = new ArrayList<>();
            agenda.dayId = startTime;
            agenda.title = Utils.getDisplayDayMS(startTime);
            agendaMap.put(startTime, agenda);
            startTime = Utils.getPlusOneDay(startTime);
        }

        for(CalenderEvent calenderEvent : events){
            long dateTime = Utils.getDayMS(calenderEvent.time);
            if(agendaMap.containsKey(dateTime)){
                agendaMap.get(dateTime).events.add(calenderEvent);
            }
        }

        for (Agenda agenda: agendaMap.values()){
            if(agenda.events.isEmpty()){
                agenda.events.add(new NoEvent());
            }
        }
        mView.showEvents(new ArrayList<>(agendaMap.values()));

    }

    private void doNetwork(long start, long end){
        // do network request
        if(Utils.isTimeInWeatherRange(start, end, System.currentTimeMillis())){
            mView.getApplicationComponent().getRestApi().getWeatherData("37.8267,-122.4233").enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    processWeather(response.body());
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Timber.i("onFailure");
                }
            });
        }
    }

    private void processWeather(WeatherResponse weatherResponse){
        if(weatherResponse != null){
            TreeMap<Long, Agenda> agendaMap = new TreeMap<>();
            List<Agenda> agendas = mView.getCurrentAgendaList();
            for(Agenda agenda : agendas){
                agendaMap.put(agenda.dayId, agenda);
            }
            DailyWeather dailyWeather = weatherResponse.daily;
            if(dailyWeather.data != null){
                for(DailyData dailyData : dailyWeather.data){
                    WeatherEvent weatherEvent = new WeatherEvent();
                    weatherEvent.time = Utils.getDayMS(dailyData.time * 1000);
                    weatherEvent.temperature = dailyData.temperatureLow +"-"+ dailyData.temperatureHigh;
                    weatherEvent.eventName = dailyData.summary;
                    if(agendaMap.containsKey(weatherEvent.time)){
                        agendaMap.get(weatherEvent.time).events.add(weatherEvent);
                    }
                }
            }

            for (Agenda agenda: agendaMap.values()){
                if(agenda.events.isEmpty()){
                    agenda.events.add(new NoEvent());
                }
            }
            mView.showEvents(new ArrayList<>(agendaMap.values()));
        }
    }

}
