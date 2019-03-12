package com.example.calendar.Data;

public final class User {

    int id;
    private String nickName;
    private int headImgId;
    private String sex;
    private int totalStarNum;
    private int totalCoinNum;
    private int totalMedalNum;
    private int gainStarYesterday;
    private int gainStarToday;

    private static int userIdGenerator = 0;

    public User(String nickName, String sex, int headImgId) {
        id = userIdGenerator++;
        this.nickName = nickName;
        this.sex = sex;
        this.headImgId = headImgId;
        totalCoinNum = 0;
        totalStarNum = 0;
        totalMedalNum = 0;
        gainStarYesterday = 0;
        gainStarToday = 0;
    }

    public void addToMedals(int amount) {
        totalMedalNum += amount;
    }

    public void addToStars(int amount) {
        totalStarNum += amount;
        gainStarToday += amount;
    }

    public void addToCoin(int amount) {
        totalCoinNum += amount;
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public int getHeadImgId() {
        return headImgId;
    }

    public String getSex() {
        return sex;
    }

    public int getTotalStarNum() {
        return totalStarNum;
    }

    public int getTotalCoinNum() {
        return totalCoinNum;
    }

    public int getTotalMedalNum() {
        return totalMedalNum;
    }

    public int getGainStarYesterday() {
        return gainStarYesterday;
    }

    public int getGainStarToday() {
        return gainStarToday;
    }


    public void setTotalStarNum(int totalStarNum) {
        this.totalStarNum = totalStarNum;
    }

    public void setTotalCoinNum(int totalCoinNum) {
        this.totalCoinNum = totalCoinNum;
    }

    public void setTotalMedalNum(int totalMedalNum) {
        this.totalMedalNum = totalMedalNum;
    }

    public void setGainStarYesterday(int gainStarYesterday) {
        this.gainStarYesterday = gainStarYesterday;
    }

    public void setGainStarToday(int gainStarToday) {
        this.gainStarToday = gainStarToday;
    }

}
