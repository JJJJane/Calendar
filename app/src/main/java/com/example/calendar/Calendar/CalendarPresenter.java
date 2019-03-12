package com.example.calendar.Calendar;

import android.util.Log;

import com.example.calendar.Data.Day;
import com.example.calendar.Data.Reward;
import com.example.calendar.Data.User;
import com.example.calendar.Data.source.DataSource;

import java.util.List;

public class CalendarPresenter implements CalendarContract.Presenter {
    private CalendarContract.View mCalendarView;
    private DataSource dataSourceImpl;


    CalendarPresenter(CalendarContract.View view, DataSource dataSourceImpl) {
        this.mCalendarView = view;
        this.dataSourceImpl = dataSourceImpl;

        mCalendarView.setPresenter(this);
    }

    @Override
    public void loadUserNow() {
        dataSourceImpl.getUserNow(new DataSource.GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                mCalendarView.showUserInfo(user);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadDays() {
        dataSourceImpl.getDays(new DataSource.LoadDaysCallback() {
            @Override
            public void onDaysLoaded(List<Day> days) {
                mCalendarView.showDays(days);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void loadDailyReward(int id) {
        dataSourceImpl.getRewardByDayId(id, new DataSource.GetRewardCallback() {
            @Override
            public void onRewardLoaded(Reward reward) {
                Log.e("test", Integer.toString(reward.getValue()));
                mCalendarView.showDailyReward(reward);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void checkToday(int dayId) {
        //取得赚取的星数和硬币数
        dataSourceImpl.getDayById(dayId, new DataSource.GetDayCallback() {
            @Override
            public void onDayLoaded(Day day) {
                int starChange = day.getDailyEarn();
                int coinChange = day.getReward().getValue();

                dataSourceImpl.addAllEarn(0, coinChange, starChange);
                loadUserNow();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

    @Override
    public void loadGoal() {
        mCalendarView.showGoal(dataSourceImpl.getDayAmount());
    }

    @Override
    public void changeUser() {
        dataSourceImpl.switchUser(new DataSource.GetUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                //载入新的用户信息
                mCalendarView.changeTheme(user.getSex());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

    @Override
    public User getUserNow() {
        return dataSourceImpl.getUserNow();
    }

    @Override
    public void start() {

    }
}
