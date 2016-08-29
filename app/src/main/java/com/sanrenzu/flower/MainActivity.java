package com.sanrenzu.flower;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.sanrenzu.flower.fragment.FenleiFragment;
import com.sanrenzu.flower.fragment.GouwucheFragment;
import com.sanrenzu.flower.fragment.ShouyeFragment;
import com.sanrenzu.flower.fragment.WodeFragment;


public class MainActivity extends AppCompatActivity {

    private Context mContext=this;
    private Button btn_main_home;
    private Button btn_main_live;
    private Button btn_main_follow;
    private Button btn_main_mine;

    private ShouyeFragment shouyeFragment;
    private FenleiFragment fenleiFragment;
    private GouwucheFragment gouwucheFragment;
    private WodeFragment wodeFragment;
    private FragmentManager manager;

    //定义选择碎片的下标
    private static final int SELECTED_SHOUYE = 0;
    private static final int SELECTED_FENLEI = 1;
    private static final int SELECTED_GOUWUCHE = 2;
    private static final int SELECTED_WODE =3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initFragment();

        selectFragment(SELECTED_SHOUYE);
    }

    private void initView() {
        btn_main_home= (Button) findViewById(R.id.btn_main_home);
        btn_main_live= (Button) findViewById(R.id.btn_main_live);
        btn_main_follow= (Button) findViewById(R.id.btn_main_follow);
        btn_main_mine= (Button) findViewById(R.id.btn_main_mine);

        btn_main_home.setSelected(true);
        btn_main_home.setTextColor(Color.parseColor("#FF7700"));
        btn_main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_main_home.setSelected(true);
                btn_main_live.setSelected(false);
                btn_main_follow.setSelected(false);
                btn_main_mine.setSelected(false);

                btn_main_home.setTextColor(Color.parseColor("#FF7700"));
                btn_main_live.setTextColor(Color.parseColor("#000000"));
                btn_main_follow.setTextColor(Color.parseColor("#000000"));
                btn_main_mine.setTextColor(Color.parseColor("#000000"));
                selectFragment(SELECTED_SHOUYE);
            }
        });
        btn_main_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_main_home.setSelected(false);
                btn_main_live.setSelected(true);
                btn_main_follow.setSelected(false);
                btn_main_mine.setSelected(false);

                btn_main_home.setTextColor(Color.parseColor("#000000"));
                btn_main_live.setTextColor(Color.parseColor("#FF7700"));
                btn_main_follow.setTextColor(Color.parseColor("#000000"));
                btn_main_mine.setTextColor(Color.parseColor("#000000"));
                selectFragment(SELECTED_FENLEI);
            }
        });
        btn_main_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_main_home.setSelected(false);
                btn_main_live.setSelected(false);
                btn_main_follow.setSelected(true);
                btn_main_mine.setSelected(false);

                btn_main_home.setTextColor(Color.parseColor("#000000"));
                btn_main_live.setTextColor(Color.parseColor("#000000"));
                btn_main_follow.setTextColor(Color.parseColor("#FF7700"));
                btn_main_mine.setTextColor(Color.parseColor("#000000"));
                selectFragment(SELECTED_GOUWUCHE);
            }
        });
        btn_main_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_main_home.setSelected(false);
                btn_main_live.setSelected(false);
                btn_main_follow.setSelected(false);
                btn_main_mine.setSelected(true);

                btn_main_home.setTextColor(Color.parseColor("#000000"));
                btn_main_live.setTextColor(Color.parseColor("#000000"));
                btn_main_follow.setTextColor(Color.parseColor("#000000"));
                btn_main_mine.setTextColor(Color.parseColor("#FF7700"));
                selectFragment(SELECTED_WODE);
            }
        });
    }


    private void initFragment() {
        //初始化碎片
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        shouyeFragment = new ShouyeFragment();
        fenleiFragment = new FenleiFragment();
        gouwucheFragment = new GouwucheFragment();
        wodeFragment = new WodeFragment();
        //将每个碎片存到事务中
        transaction.add(R.id.layout_container, shouyeFragment);
        transaction.add(R.id.layout_container, fenleiFragment);
        transaction.add(R.id.layout_container, gouwucheFragment);
        transaction.add(R.id.layout_container, wodeFragment);
        //展现
        transaction.commit();
    }

    //设置选择的碎片下标
    private void selectFragment(int position) {
        //开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        //将所有的碎片先隐藏
        transaction.hide(shouyeFragment).hide(fenleiFragment).hide(gouwucheFragment).hide(wodeFragment);
        switch (position) {
            //根据选择的小标，显示碎片
            case SELECTED_SHOUYE:
                transaction.show(shouyeFragment);
                break;
            case SELECTED_FENLEI:
                transaction.show(fenleiFragment);
            case SELECTED_GOUWUCHE:
                transaction.show(gouwucheFragment);
                break;
            case SELECTED_WODE:
                transaction.show(wodeFragment);
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//退出窗口
        if (keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("确认退出？");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
