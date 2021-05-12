package io.github.homework.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;
import io.github.homework.module.CommonFragment;

 
public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<String> title;
    private List<Integer> type;

    public PagerAdapter(@NonNull FragmentManager fm, List<String> title, List<Integer> type) {
        super(fm);
        this.title = title;
        this.type = type;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return CommonFragment.getInstance(type.get(position));
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
