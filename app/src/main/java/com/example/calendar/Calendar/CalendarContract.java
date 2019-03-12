package com.example.calendar.Calendar;

import com.example.calendar.BasePresenter;
import com.example.calendar.BaseView;
import com.example.calendar.Data.Day;
import com.example.calendar.Data.Reward;
import com.example.calendar.Data.User;

import java.util.List;

public interface CalendarContract {
    interface View extends BaseView<Presenter> {
        void showUserInfo(User userNow);

        void showDays(List<Day> days);

        void showDailyReward(Reward reward);

        void showGoal(int dayAmount);

        void changeTheme(String sex);
    }

    interface Presenter extends BasePresenter {

        void loadUserNow();

        void loadDailyReward(int dayId);

        void loadDays();

        void checkToday(int dayId);  //一日打卡

        void loadGoal();

        void changeUser();

        User getUserNow();
    }
}
