package com.example.test.activity.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.test.R;

/**
 * @author 65667
 */
public class MusicActivity extends AppCompatActivity {

    private ImageButton stop, play;
    private TextView songName, singer;

    private ActivityReceiver activityReceiver = null;

    private int status = 0x11;

    private String[] songNames = new String[] {"生命之歌", "平凡之路", "夜空中最亮的星"};
    private String[] singers = new String[] {"许巍", "朴素", "张杰"};

    private MyListener myListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        stop = (ImageButton) findViewById(R.id.stop);
        play = (ImageButton) findViewById(R.id.play);
        songName = (TextView) findViewById(R.id.songName);
        singer = (TextView) findViewById(R.id.singer);

        activityReceiver = new ActivityReceiver();
        IntentFilter intentFilter = new IntentFilter("com.example.test.activity.music.update");
        registerReceiver(activityReceiver, intentFilter);

        Intent intent = new Intent(MusicActivity.this, MusicService.class);
        startService(intent);

        myListener = new MyListener();
        play.setOnClickListener(myListener);
        stop.setOnClickListener(myListener);
    }


    private class ActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int update = intent.getIntExtra("update", -1);
            int current = intent.getIntExtra("current", -1);
            switch (update) {
                case 0x11:
                    play.setBackgroundResource(R.drawable.play);
                    status = 0x11;
                    break;
                case 0x12:
                    play.setBackgroundResource(R.drawable.pause);
                    status = 0x12;
                    break;
                case 0x13:
                    play.setBackgroundResource(R.drawable.play);
                    status = 0x13;
                    break;
                default:
                    break;
            }

            if (current >= 0) {
                songName.setText(songNames[current]);
                singer.setText(singers[current]);
            }
        }
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("com.example.test.activity.music.control");
            switch (v.getId()) {
                case R.id.stop:
                    intent.putExtra("control", 2);
                    break;
                case R.id.play:
                    intent.putExtra("control", 1);
                    break;
                default:
                    break;
            }
            sendBroadcast(intent);
        }
    }
}