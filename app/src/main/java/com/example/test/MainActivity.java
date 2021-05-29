package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.activity.OrderFoodActivity;
import com.example.test.activity.TextActivity;
import com.example.test.activity.chaundi.ChuandiActivity;
import com.example.test.activity.data.SharedPreferencesActivity;
import com.example.test.activity.music.MusicActivity;
import com.example.test.activity.sqllitedemo.SqlLiteDemoActivity;
import com.example.test.activity.url.UrlActivity;

/**
 * @author 65667
 * goal: 移动点餐系统
 */
public class MainActivity extends AppCompatActivity {

    private Button text_Button, order_Food, btn_url, btn_Chuandi, bt_dialog, bt_share;
    private Button bt_sql_lite, music_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        text_Button = (Button) findViewById(R.id.text_view);
        order_Food = (Button) findViewById(R.id.order_food_demo);
        btn_url = (Button) findViewById(R.id.test_url);
        btn_Chuandi = (Button) findViewById(R.id.chuandi);
        bt_dialog = (Button) findViewById(R.id.dialog);
        bt_share = (Button) findViewById(R.id.share);
        bt_sql_lite = (Button)findViewById(R.id.sql_lite);
        music_player = (Button) findViewById(R.id.music_player);
        music_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicIntent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(musicIntent);
            }
        });
        bt_sql_lite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sqlLiteIntent = new Intent(MainActivity.this, SqlLiteDemoActivity.class);
                startActivity(sqlLiteIntent);
            }
        });
        text_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent text_intent = new Intent(MainActivity.this, TextActivity.class);
                startActivity(text_intent);
            }
        });
        order_Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent order_food_intent = new Intent(MainActivity.this, OrderFoodActivity.class);
                startActivity(order_food_intent);
            }
        });
        btn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test_url = new Intent(MainActivity.this, UrlActivity.class);
                startActivity(test_url);
            }
        });
        btn_Chuandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chuandi_intent = new Intent(MainActivity.this, ChuandiActivity.class);
                startActivity(chuandi_intent);
            }
        });

        bt_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("鬼魅一笑");
                builder.setMessage("澳子哥帅吗？");
                builder.setCancelable(false);
                builder.setPositiveButton("帅", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "人家都不好意思了呢！", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("巨丑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "是不是不想在实验室混了！", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
//                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//                progressDialog.setMessage("内容");
//                progressDialog.setTitle("标题");
//                progressDialog.show();

                final LoginDialog loginDialog = new LoginDialog(MainActivity.this, R.style.Dialog);
                loginDialog.setTitle("用户登录");
                loginDialog.show();
                loginDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(MainActivity.this, loginDialog.username, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(MainActivity.this, SharedPreferencesActivity.class);
                startActivity(shareIntent);
            }
        });
    }
}