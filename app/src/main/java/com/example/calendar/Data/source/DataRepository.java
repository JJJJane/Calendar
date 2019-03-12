package com.example.calendar.Data.source;

import com.example.calendar.Data.Day;
import com.example.calendar.Data.User;
import com.example.calendar.Data.source.local.DataBase;

public class DataRepository implements DataSource {
    private DataBase dataBase;

    public DataRepository() {
        dataBase = DataBase.getInstance();
    }

    @Override
    public void getDayById(int id, GetDayCallback callback) {
        for (Day day : dataBase.getDays()) {
            if (day.getId() == id) {
                callback.onDayLoaded(day);
                return;
            }
        }
        callback.onDataNotAvailable();
    }

    @Override
    public void getRewardByDayId(int id, GetRewardCallback callback) {
        for (Day day : dataBase.getDays()) {
            if (day.getId() == id) {
                callback.onRewardLoaded(day.getReward());
                return;
            }
        }
        callback.onDataNotAvailable();
    }

    @Override
    public void getDays(LoadDaysCallback callback) {
        if (dataBase.getDays() != null && dataBase.getDays().size() != 0)
            callback.onDaysLoaded(dataBase.getDays());
        else callback.onDataNotAvailable();
    }

    @Override
    public void getUserNow(GetUserCallback callback) {
        if (dataBase.getUsers() != null && dataBase.getUsers().size() != 0)
            callback.onUserLoaded(dataBase.getUserNow());
        else callback.onDataNotAvailable();
    }

    @Override
    public void addAllEarn(int medal, int coin, int star) {
        User user = dataBase.getUserNow();
        user.addToMedals(medal);
        user.addToStars(star);
        user.addToCoin(coin);
    }

    @Override
    public int getDayAmount() {
        return dataBase.getDays().size();
    }

    @Override
    public void switchUser(GetUserCallback callback) {
        dataBase.switchUser();
        callback.onUserLoaded(dataBase.getUserNow());
    }

    @Override
    public User getUserNow() {
        return dataBase.getUserNow();
    }
}
