package com.example.calendar.Calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.calendar.Data.Day;
import com.example.calendar.Data.Reward;
import com.example.calendar.Data.User;
import com.example.calendar.Data.source.DataRepository;
import com.example.calendar.R;
import com.example.calendar.Reward.RewardActivity;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

//这个类组合Fragment与Presenter，进行显示
public class CalendarActivity extends AppCompatActivity implements CalendarContract.View {
    //视图层持有P层，P层持有V与M层
    private CalendarContract.Presenter mPresenter = new CalendarPresenter(this, new DataRepository());
    private View shadeView;
    private View shadeView2;
    private CardView dailyReward;
    private LinearLayout llRank;
    private Animation mShowAction;
    private Animation mHideAction;
    private Animation mSlowInAction;
    private Animation mSlowOutAction;
    private LinearLayout btConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter.getUserNow().getSex().equals("male"))
            setTheme(R.style.MyTheme_Male);
        else
            setTheme(R.style.MyTheme_Female);
        setContentView(R.layout.calendar_main);
        shadeView = findViewById(R.id.shadeView);
        shadeView2 = findViewById(R.id.shadeView2);
        llRank = findViewById(R.id.llRank);
        FloatingActionButton btUser = findViewById(R.id.btUser);
        dailyReward = findViewById(R.id.dailyRewardView);
        btConfirm = findViewById(R.id.btConfirm);
        FloatingActionButton btReward = findViewById(R.id.btReward);
        ImageView imgOther = findViewById(R.id.imgOther);

        //设置下拉菜单弹出监听
        btUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示阴影
                shadeView.setVisibility(View.VISIBLE);
                //设置动画以及显示布局
                mShowAction = AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.dialog_top_in);
                llRank.startAnimation(mShowAction);
                llRank.setVisibility(View.VISIBLE);
            }
        });

        //阴影点击事件，隐藏阴影和布局
        shadeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置动画以及显示布局
                mHideAction = AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.dialog_top_out);
                //隐藏阴影
                llRank.startAnimation(mHideAction);
                shadeView.setVisibility(View.GONE);
                llRank.setVisibility(View.GONE);
            }
        });

        shadeView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlowOutAction = AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.dialog_top_out);
                dailyReward.startAnimation(mSlowOutAction);
                shadeView2.setVisibility(View.GONE);
                dailyReward.setVisibility(View.GONE);
            }
        });

        btReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, RewardActivity.class);
                startActivity(intent);
            }
        });

        imgOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.changeUser();
            }
        });

        //初始化信息
        mPresenter.loadDays();
        mPresenter.loadUserNow();
        mPresenter.loadGoal();
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUserInfo(User userNow) {
        TextView metalAmount = findViewById(R.id.textMetal);
        TextView coinAmount = findViewById(R.id.textCoin);
        TextView textName = findViewById(R.id.textNameUser);
        TextView starAmount = findViewById(R.id.textStar);
        TextView yesterdayAmount = findViewById(R.id.textStarGainYesterday);
        TextView gainToday = findViewById(R.id.textStarGainToday);
        TextView otherName = findViewById(R.id.textNameOther);
        ImageView userImg = findViewById(R.id.imgUser);
        ImageView otherImg = findViewById(R.id.imgOther);


        metalAmount.setText(Integer.toString(userNow.getTotalMedalNum()));
        coinAmount.setText(Integer.toString(userNow.getTotalCoinNum()));
        textName.setText(userNow.getNickName());
        starAmount.setText(Integer.toString(userNow.getTotalStarNum()));
        yesterdayAmount.setText(Integer.toString(userNow.getGainStarYesterday()));
        gainToday.setText(Integer.toString(userNow.getGainStarToday()));
        userImg.setImageResource(userNow.getHeadImgId());
    }


    @Override
    public void showDays(List<Day> days) {
        TableLayout tableLayout = findViewById(R.id.contentView);
        int count = 0;
        TableRow row = new TableRow(getApplicationContext());

        for (Day day : days) {
            final ItemView itemView = new ItemView(this, day);
            row.addView(itemView);
            count++;
            TableRow.LayoutParams layoutParams = (TableRow.LayoutParams) itemView.getLayoutParams();
            layoutParams.setMarginStart(2);
            layoutParams.setMarginEnd(2);
            layoutParams.topMargin = 3;
            layoutParams.bottomMargin = 3;
            itemView.setImg(day.getReward().getImgId());
            itemView.setStarAmount(day.getDailyEarn());
            itemView.setClickable(true);
            itemView.setFocusable(true);
            //添加监听

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.loadDailyReward(itemView.getIdToDay());
                    mSlowInAction = AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.dialog_slow_in);
                    dailyReward.startAnimation(mSlowInAction);
                    dailyReward.setVisibility(View.VISIBLE);
                    shadeView2.setVisibility(View.VISIBLE);

                    //签到确认按钮
                    btConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSlowOutAction = AnimationUtils.loadAnimation(CalendarActivity.this, R.anim.dialog_top_out);
                            dailyReward.startAnimation(mSlowOutAction);
                            mPresenter.checkToday(itemView.getIdToDay());
                            shadeView2.setVisibility(View.GONE);
                            dailyReward.setVisibility(View.GONE);
                        }
                    });
                }
            });
            if ((count % 7 == 0 && count != 0) || count == days.size()) {
                tableLayout.addView(row);
                row = new TableRow(getApplicationContext());
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showDailyReward(Reward reward) {
        ImageView img = dailyReward.findViewById(R.id.imgDailyReward);
        img.setImageResource(reward.getImgId());
        TextView value = dailyReward.findViewById(R.id.textValue);
        value.setText(Integer.toString(reward.getValue()));
    }

    @Override
    public void showGoal(int dayAmount) {
        TextView goal = findViewById(R.id.textGoal);
        TextView during = findViewById(R.id.textDuring);
        String s1 = "7月3日 - 7月" + Integer.toString(dayAmount + 3) + "日";
        String s2 = "坚持打卡，\n在 " + Integer.toString(dayAmount) + " 天内消灭蛀牙";
        during.setText(s1);
        goal.setText(s2);
    }


    @Override
    public void changeTheme(String sex) {
        if (sex.equals("male"))
            setTheme(R.style.MyTheme_Male);
        else
            setTheme(R.style.MyTheme_Female);
        recreate();
    }
}
