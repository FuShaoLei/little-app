package io.github.homework.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import io.github.homework.module.MeFragment;
import io.github.homework.module.MusicFragment;

/**
 * @Auther: fushaolei
 * @datetime: 2021/5/9
 * @desc:
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<String> title;

    public PagerAdapter(@NonNull FragmentManager fm, List<String> title) {
        super(fm);
        this.title = title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d("==>", getPageTitle(position) + "");
        String name = getPageTitle(position).toString();
        Fragment fragment = new Fragment();
        switch (name) {
            case "音乐":
                fragment = new MusicFragment();
                break;
            case "我的":
                fragment = new MeFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return title == null ? 0 : title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
