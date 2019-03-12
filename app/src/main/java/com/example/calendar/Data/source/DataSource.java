package com.example.calendar.Data.source;

import com.example.calendar.Data.Day;
import com.example.calendar.Data.Reward;
import com.example.calendar.Data.User;

import java.util.List;

public interface DataSource {
    interface LoadDaysCallback {
        void onDaysLoaded(List<Day> days);

        void onDataNotAvailable();
    }

    interface GetDayCallback {
        void onDayLoaded(Day day);

        void onDataNotAvailable();
    }

    interface GetUserCallback {
        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

    interface GetRewardCallback {
        void onRewardLoaded(Reward reward);

        void onDataNotAvailable();
    }

    int getDayAmount();

    void addAllEarn(int medal, int coin, int star);

    void getDayById(int id, GetDayCallback callback);

    void switchUser(GetUserCallback callback);

    void getRewardByDayId(int id, GetRewardCallback callback);

    void getDays(LoadDaysCallback callback);

    void getUserNow(GetUserCallback userCallback);

    User getUserNow();
}
