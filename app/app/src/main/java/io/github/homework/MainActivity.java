package io.github.homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.homework.adapter.PagerAdapter;
import io.github.homework.constant.Constant;
import io.github.homework.database.MyDatabaseHelper;
import io.github.homework.entity.Music;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private PagerAdapter pagerAdapter;
    private List<Music> musicList;
    private List<String> titleList;
    private List<Integer> typeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);


        titleList = new ArrayList<>();
        titleList.add(Constant.FIRST_NAME);
        titleList.add(Constant.SECOND_NAME);

        typeList = new ArrayList<>();
        typeList.add(Constant.TYPE_FIRST);
        typeList.add(Constant.TYPE_SECOND);


        for (String name : titleList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(name));
        }
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), titleList, typeList);

        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabIndicatorFullWidth(false);
    }

    /**
     * 初始化数据库
     */
    private void initDatabase() {
        musicList = new ArrayList<>();
        musicList.clear();
        musicList.add(new Music("Stairway", "Patrick Patrikios", R.drawable.cover01, R.raw.stairway,1));
        musicList.add(new Music("Awful", "josh pan", R.drawable.cover02, R.raw.awful,1));
        musicList.add(new Music("Voices", "Patrick Patrikios", R.drawable.cover03, R.raw.voices,1));
        musicList.add(new Music("Breatha", "josh pan", R.drawable.cover04, R.raw.breatha,1));
        musicList.add(new Music("19th Floor", "Bobby Richards", R.drawable.cover05, R.raw.floor,1));

        musicList.add(new Music("袖手旁观", "张宇", R.drawable.cover05, R.raw.chinesesong01,2));
        musicList.add(new Music("暗香", "沙宝亮", R.drawable.cover01, R.raw.chinesesong02,2));
        musicList.add(new Music("无地自容", "黑豹乐队", R.drawable.cover02, R.raw.chinesesong03,2));
        musicList.add(new Music("吻的太逼真", "张敬轩", R.drawable.cover05, R.raw.chinesesong04,2));
        musicList.add(new Music("春夏秋冬", "张国荣", R.drawable.cover05, R.raw.chinesesong05,2));


        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "MusicData.db", null, 1);
        // 创建数据库
        dbHelper.getWritableDatabase();
        SQLiteDatabase mSqlitedb = dbHelper.getWritableDatabase();
        // 清空数据库
        mSqlitedb.execSQL("DELETE FROM Music");
        // 插入预设数据
        for (int i = 0; i < musicList.size(); i++) {
            Music music = musicList.get(i);
            ContentValues values = new ContentValues();
            values.put("name", music.getName());
            values.put("author", music.getAuthor());
            values.put("cover", music.getCover());
            values.put("resrouce", music.getResource());
            values.put("type",music.getType());
            mSqlitedb.insert("Music", null, values);
        }
    }
}