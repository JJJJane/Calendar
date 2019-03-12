package com.example.calendar.Data;

//这个类是Recycler控件中单项，即作为日历的单项的实体
public final class Day {
    private final int id;
    private boolean completed;
    private int dailyEarn;
    private Reward reward;

    private static int dayIdGenerator = 0;

    public Day(int dailyEarn, boolean isCompleted, Reward reward) {
        this.dailyEarn = dailyEarn;
        this.reward = reward;
        this.id = dayIdGenerator++;
        this.completed = isCompleted;
    }

    public int getDailyEarn() {
        return dailyEarn;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }


    public int getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }
}
