package io.github.homework.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import io.github.homework.R;
import io.github.homework.constant.Constant;

public class MusicService extends Service {
    private MediaPlayer mp;

    private int musicId;

    public MusicService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("==>", "service create");
    }

    @Override
    public void onStart(Intent intent, final int startId) {
        super.onStart(intent, startId);
        Log.d("==>", "service start");
        int newMusic = intent.getIntExtra(Constant.MUSIC, R.raw.breatha);

        boolean isplay = intent.getBooleanExtra(Constant.STATUS, true);

        // 如果传过来的音乐文件和之前的不同，就要终止之前的
        if (newMusic != musicId) {
            musicId = newMusic;
            if (mp != null && mp.isPlaying()) {
                mp.stop();
            }
            mp = MediaPlayer.create(this, musicId);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSelf();
                }
            });
            mp.start();
        }
        Intent statusIntent = new Intent(Constant.BROADCAST_ACTION);
        statusIntent.putExtra(Constant.ACTION, 1);
        sendBroadcast(statusIntent);

        if (!isplay) {
            if (mp != null && mp.isPlaying()) {
                mp.pause();
            }
            Intent intent1 = new Intent(Constant.BROADCAST_ACTION);
            intent1.putExtra(Constant.ACTION, 2);
            sendBroadcast(intent1);
        }
        if (isplay) {
            if (mp != null && !mp.isPlaying()) {
                mp.start();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
