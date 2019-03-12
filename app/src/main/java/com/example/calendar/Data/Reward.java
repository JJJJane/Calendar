package com.example.calendar.Data;

import android.support.annotation.Nullable;

public final class Reward {

    private int id;
    private int value;  //以最低级的星为单位
    private int imgId;
    private String name;


    private static int rewardIdGenerator = 0;

    public Reward(int value, int imgId, @Nullable String name) {
        this.id = rewardIdGenerator++;
        this.value = value;
        this.imgId = imgId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public int getImgId() {
        return imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
