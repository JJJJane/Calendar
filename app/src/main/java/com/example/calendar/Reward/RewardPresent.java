package com.example.calendar.Reward;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import com.example.calendar.Data.User;
import com.example.calendar.Data.source.DataSource;

public class RewardPresent implements RewardContract.Presenter {
    private RewardContract.View mCalendarView;
    private DataSource dataSourceImpl;


    RewardPresent(RewardContract.View view, DataSource dataSourceImpl) {
        this.mCalendarView = view;
        this.dataSourceImpl = dataSourceImpl;
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
    public User getUserNow() {
        return dataSourceImpl.getUserNow();
    }

    @Override
    public void start() {

    }
}
