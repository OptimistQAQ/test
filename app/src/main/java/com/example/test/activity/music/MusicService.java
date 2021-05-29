package com.example.test.activity.music;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * @author 65667
 */
public class MusicService extends Service {

    private ServiceReceiver serviceReceiver = null;
    private int status = 0x11;

    String []musics = new String[] {"life.mp3", "road.mp3", "star.mp3"};
    int current = 0;

    private MediaPlayer mediaPlayer = null;
    private AssetManager assertManager = null;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        serviceReceiver = new ServiceReceiver();
        IntentFilter intentFilter = new IntentFilter("com.example.test.activity.music.control");
        registerReceiver(serviceReceiver, intentFilter);
        mediaPlayer = new MediaPlayer();
        assertManager = getAssets();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                if (current >= 3) {
                    current = 0;
                }
                Intent sendIntent = new Intent("com.example.test.activity.music.update");
                sendIntent.putExtra("update", current);
                sendBroadcast(sendIntent);
                prepareAndPlay(musics[current]);
            }
        });
    }

    private class ServiceReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra("control", -1);
            switch (control) {
                case 1:
                    if (status == 0x11){
                        //开始播放
                        status = 0x12;
                        prepareAndPlay(musics[current]);
                    }
                    else if(status == 0x12) {
                        status = 0x13;
                    }
                    else if(status == 0x13) {
                        mediaPlayer.start();
                        status = 0x12;
                    }
                    break;
                case 2:
                    if(status == 0x12 || status == 0x13) {
                        status = 0x11;
                    }
                    break;
                default:
                    break;
            }
            Intent sendIntent = new Intent("com.example.test.activity.music.update");
            sendIntent.putExtra("update", status);
            sendIntent.putExtra("current", current);
            sendBroadcast(sendIntent);
        }
    }

    private void prepareAndPlay(String music) {
        try {
            AssetFileDescriptor assetFileDescriptor = assertManager.openFd(music);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
