package io.github.homework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.github.homework.R;
import io.github.homework.constant.Constant;
import io.github.homework.entity.Music;
import io.github.homework.module.MusicActivity;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Music> dataList;

    public ListViewAdapter(Context mContext, List<Music> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return dataList != null ? dataList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_music, null);
        }
        TextView num = convertView.findViewById(R.id.music_number);
        TextView name = convertView.findViewById(R.id.music_name);
        TextView author = convertView.findViewById(R.id.music_author);

        Music music = dataList.get(position);
        num.setText(position + 1 + "");
        name.setText(music.getName());
        author.setText(music.getAuthor());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MusicActivity.class);
                intent.putExtra(Constant.ENTITY, dataList.get(position));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}
