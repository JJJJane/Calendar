package com.example.calendar.Calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calendar.Data.Day;
import com.example.calendar.R;

public class ItemView extends CardView {


    int idToDay;

    public ItemView(@NonNull Context context, Day day) {
        super(context);
        idToDay = day.getId();
        LayoutInflater.from(context).inflate(R.layout.calendar_content_item, this);
        ImageView img = findViewById(R.id.imgDailyRewardInC);
        img.setImageResource(day.getReward().getImgId());
        TextView text = findViewById(R.id.textStarsNumForItem);
        text.setText(Integer.toString(day.getReward().getValue()));
    }

    public int getIdToDay() {
        return idToDay;
    }

    public void setImg(int imgId) {
        ImageView img = findViewById(R.id.imgDailyRewardInC);
        img.setImageResource(imgId);
    }

    public void setStarAmount(int amount) {
        TextView text = findViewById(R.id.textStarsNumForItem);
        text.setText(Integer.toString(amount));
    }


}
