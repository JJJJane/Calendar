package com.example.calendar.Reward;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calendar.Data.User;
import com.example.calendar.Data.source.DataRepository;
import com.example.calendar.R;

import static com.google.common.base.Preconditions.checkNotNull;


public class RewardActivity extends AppCompatActivity implements RewardContract.View {
    //视图层持有P层，P层持有V与M层
    private RewardContract.Presenter mPresenter;
    private ImageView imgBadge1;
    private ImageView imgBadge2;
    private ImageView imgBadge3;
    private ImageView imgTree;
    private ImageView imgAmusement;
    private ImageView imgFood;
    private ImageView imgTooth;
    private ConstraintLayout layout;
    private FloatingActionButton btHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new RewardPresent(this, new DataRepository());
        if (mPresenter.getUserNow().getSex().equals("male"))
            setTheme(R.style.MyTheme_Male);
        else
            setTheme(R.style.MyTheme_Female);
        setContentView(R.layout.reward_main);
        btHome = findViewById(R.id.btHome);
        imgBadge1 = findViewById(R.id.imgBadge1);
        imgBadge2 = findViewById(R.id.imgBadge2);
        imgBadge3 = findViewById(R.id.imgBadge3);
        imgTree = findViewById(R.id.imgTree);
        imgAmusement = findViewById(R.id.imgAmusement);
        imgFood = findViewById(R.id.imgFood);
        imgTooth = findViewById(R.id.imgTooth);
        layout = findViewById(R.id.back1);

        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        imgBadge1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout,
                        "赚取 75 个硬币以获得奖牌", Snackbar.LENGTH_SHORT).show();
            }
        });

        imgBadge2.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout,
                        "赚取 100 个硬币以获得奖牌", Snackbar.LENGTH_SHORT).show();
            }
        });

        imgBadge3.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout,
                        "赚取 50 个硬币以获得奖牌", Snackbar.LENGTH_SHORT).show();
            }
        });

        imgTree.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout,
                        "赚取 150 个硬币以获得智慧树", Snackbar.LENGTH_SHORT).show();
            }
        });

        imgAmusement.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout,
                        "赚取 300 个硬币以获得游乐场入场券", Snackbar.LENGTH_SHORT).show();
            }
        });

        imgFood.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout,
                        "赚取 200 个硬币以获得一顿美餐", Snackbar.LENGTH_SHORT).show();
            }
        });

        imgTooth.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Snackbar.make(layout,
                        "达成目标以获得护牙勋章", Snackbar.LENGTH_SHORT).show();
            }
        });
        mPresenter.loadUserNow();
    }

    @Override
    public void setPresenter(RewardContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showUserInfo(User userNow) {
        TextView metalAmount = findViewById(R.id.textMetal);
        TextView coinAmount = findViewById(R.id.textCoin);

        metalAmount.setText(Integer.toString(userNow.getTotalMedalNum()));
        coinAmount.setText(Integer.toString(userNow.getTotalCoinNum()));
    }
}
