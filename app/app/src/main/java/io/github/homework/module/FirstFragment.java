package io.github.homework.module;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.homework.R;
import io.github.homework.adapter.ListViewAdapter;
import io.github.homework.database.MyDatabaseHelper;
import io.github.homework.entity.Music;

/**
 * @Auther: fushaolei
 * @datetime: 2021/5/9
 * @desc:
 */
public class FirstFragment extends Fragment {
    private final static String TYPE = "type";
    private View mView;
    private ListView listView;
    private ListViewAdapter adapter;
    private List<Music> dataList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frament_music, container, false);
        initData();
        initView();
        return mView;
    }

    /**
     * 从数据库中拿取数据
     */
    private void initData() {
        Bundle bundle = getArguments();
        int mType = bundle.getInt(TYPE, 0);
        Log.d("==>", "type = " + mType);
        dataList = new ArrayList<>();
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(getContext(), "MusicData.db", null, 1);
        // 查询数据
        SQLiteDatabase mSqlitedb = dbHelper.getWritableDatabase();
        Cursor cursor = mSqlitedb.rawQuery("select * from Music where type = ?", new String[]{mType + ""});
        if (cursor.moveToNext()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int cover = cursor.getInt(cursor.getColumnIndex("cover"));
                int resrouce = cursor.getInt(cursor.getColumnIndex("resrouce"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                dataList.add(new Music(id, name, author, cover, resrouce, type));
            } while (cursor.moveToNext());
        }
        cursor.close();
        for (Music m : dataList) {
            Log.d("==>", m.toString());
        }
    }

    public static FirstFragment getInstance(int type) {
        FirstFragment fragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initView() {
        listView = mView.findViewById(R.id.list_view);


        adapter = new ListViewAdapter(getContext(), dataList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
