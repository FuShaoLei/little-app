package io.github.homework.module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.github.homework.R;
import io.github.homework.constant.Constant;
import io.github.homework.entity.Music;
import io.github.homework.service.MusicService;


public class MusicActivity extends AppCompatActivity {
    private Music music;
    private ImageView cover;
    private TextView name;
    private TextView author;

    private Button controller;

    private boolean isPlay;// 用来表示状态

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        cover = findViewById(R.id.iv_cover);
        name = findViewById(R.id.music_name);
        author = findViewById(R.id.music_author);
        controller = findViewById(R.id.btn_controller);

        Intent intent = getIntent();
        music = (Music) intent.getSerializableExtra(Constant.ENTITY);
        Log.d("==>", music.toString());

        cover.setImageResource(music.getCover());
        name.setText(music.getName());
        author.setText(music.getAuthor());


        // 动态注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BROADCAST_ACTION);
        // 注册广播
        registerReceiver(broadcastReceiver, intentFilter);

        Intent musicIntent = new Intent(this, MusicService.class);
        musicIntent.putExtra(Constant.MUSIC, music.getResource());
        startService(musicIntent);

        controller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("==>", "点击了controller");
                Intent musicIntent = new Intent(MusicActivity.this, MusicService.class);
                musicIntent.putExtra(Constant.MUSIC, music.getResource());
                isPlay = !isPlay;
                Log.d("==>", "isPlay = " + isPlay);
                musicIntent.putExtra(Constant.STATUS, isPlay);
                startService(musicIntent);
            }
        });
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra(Constant.ACTION, -1);
            switch (status) {
                case 1:
                    Log.d("==>", "播放中...");
                    isPlay = true;
                    controller.setBackgroundResource(R.drawable.ic_pause);// 播放中
                    break;
                case 2:
                    Log.d("==>", "暂停中...");
                    isPlay = false;
                    controller.setBackgroundResource(R.drawable.ic_play);// 暂停中
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
