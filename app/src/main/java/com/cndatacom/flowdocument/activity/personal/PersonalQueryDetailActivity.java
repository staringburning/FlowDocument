package com.cndatacom.flowdocument.activity.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.cndatacom.flowdocument.R;
import com.cndatacom.flowdocument.fragment.personal.DonationDetailFragment;
import com.cndatacom.flowdocument.fragment.personal.OrderDetailFragment;
import com.cndatacom.flowdocument.fragment.personal.UseDetailFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

public class PersonalQueryDetailActivity extends FragmentActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"订购明细", "启用明细", "转赠明细"};
    private OrderDonationPagerAdapter mAdapter;
    private int which_select = -1;
    private final String WHICH_SELECT = "which_select";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_personal_query_detail, null);
        setContentView(view);
        initView();
    }

    private void initView() {
        which_select = getIntent().getIntExtra(WHICH_SELECT, -1);

        mFragments.add(0, OrderDetailFragment.newInstance());
        mFragments.add(1, UseDetailFragment.newInstance());
        mFragments.add(2, DonationDetailFragment.newInstance());

        ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new OrderDonationPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        SlidingTabLayout tabLayout_9 = (SlidingTabLayout) findViewById(R.id.tl_9_order_donation_activity);
        tabLayout_9.setViewPager(vp);
        if (which_select != -1) {
            tabLayout_9.setCurrentTab(which_select);
        }
    }

    private class OrderDonationPagerAdapter extends FragmentPagerAdapter {
        public OrderDonationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.right, R.anim.right_hide);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
