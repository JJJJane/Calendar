package com.example.calendar.Data.source.local;


import com.example.calendar.Data.Day;
import com.example.calendar.Data.Reward;
import com.example.calendar.Data.User;
import com.example.calendar.R;

import java.util.ArrayList;
import java.util.List;

//假想数据库，其中保存图片、用户信息以及其它数据
public class DataBase {
    private List<User> users = new ArrayList<>();
    private List<Day> days = new ArrayList<>();
    private int userNow = 0;
    private static DataBase instance = new DataBase();

    private DataBase() {
        //生成数据
        //加入男性用户
        User male = new User("宝宝_boy", "male", R.drawable.head1);
        male.setGainStarToday(0);
        male.setGainStarYesterday(8);
        male.setTotalStarNum(10);
        male.setTotalCoinNum(5);
        male.setTotalMedalNum(2);

        users.add(male);
        //加入女性用户
        User female = new User("宝宝_girl", "female", R.drawable.head2);
        female.setGainStarToday(0);
        female.setGainStarYesterday(3);
        female.setTotalStarNum(5);
        female.setTotalCoinNum(7);
        female.setTotalMedalNum(1);
        users.add(female);
        //载入每日奖品，随机生成每天，包括获得的代币与奖品
        List<Reward> dailyReward = new ArrayList<>();
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize2, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize3, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize4, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize5, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize7, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize8, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize9, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize10, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize11, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize12, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize13, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize14, null));
        dailyReward.add(new Reward((6 + (int) (Math.random() * 9)), R.drawable.prize15, null));
        int maxDay = 22 + (int) (Math.random() * 5);
        for (int i = 0; i < maxDay; i++) {
            Reward reward = dailyReward.get((int) (Math.random() * 10));
            int dailyEarn = 6 + (int) (Math.random() * 5);
            days.add(new Day(dailyEarn, false, reward));
        }
    }

    public static DataBase getInstance() {
        if (instance == null)
            instance = new DataBase();
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Day> getDays() {
        return days;
    }

    public User getUserNow() {
        return users.get(userNow);
    }

    public void switchUser() {
        if (userNow == 0)
            userNow = 1;
        else userNow = 0;
    }
}
