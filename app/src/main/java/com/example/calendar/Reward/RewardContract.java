package com.example.calendar.Reward;

import com.example.calendar.BasePresenter;
import com.example.calendar.BaseView;
import com.example.calendar.Data.User;

public interface RewardContract {
    interface View extends BaseView<Presenter> {
        void showUserInfo(User userNow);
    }

    interface Presenter extends BasePresenter {
        void loadUserNow();

        User getUserNow();
    }
}
