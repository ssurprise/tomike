package com.skx.tomike.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author shiguotao
 * <p>
 * FragmentAdapter 基类
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mPageList;
    private List<String> mPageTitleList;

    public BaseFragmentAdapter(FragmentManager fm) {
        this(fm, null);
    }

    public BaseFragmentAdapter(FragmentManager fragmentManager, List<Fragment> lists) {
        this(fragmentManager, lists, null);
    }

    public BaseFragmentAdapter(FragmentManager fragmentManager, List<Fragment> lists, List<String> pageTitleList) {
        super(fragmentManager);
        this.mPageList = lists;
        this.mPageTitleList = pageTitleList;
    }

    public void setLoadFragment(List<Fragment> lists) {
        this.mPageList = lists;
    }

    @Override
    public Fragment getItem(int location) {
        return mPageList.get(location);
    }

    @Override
    public int getCount() {
        return mPageList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (mPageTitleList == null || mPageTitleList.isEmpty()) {
            return null;
        }
        return mPageTitleList.get(position);
    }
}
