package com.osiris.food.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.home.fragment.ApplyFragment;
import com.osiris.food.home.fragment.ExamFragment;
import com.osiris.food.home.fragment.StudyFragment;
import com.osiris.food.mine.fragment.AllFragment;
import com.osiris.food.mine.fragment.PaidFragment;
import com.osiris.food.mine.fragment.UnPaidFragment;
import com.osiris.food.view.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity {


	@BindView(R.id.tab_strip)
	PagerSlidingTabStrip tab_strip;
	@BindView(R.id.viewPager)
	ViewPager mViewPager;
	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;

	private String[] title;



	@Override
	public int getLayoutResId() {
		return R.layout.activity_my_order;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.txt_title_mine_order));

		title = new String[]{getResources().getString(R.string.all), getResources().getString(R.string
				.paid),getResources().getString(R.string.unpaid)};
		mViewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager()));
		tab_strip.setViewPager(mViewPager);
		tab_strip.setTextSize((int) getResources().getDimension(R.dimen.sp16));

	}

	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
		}
	}

	private class myPagerAdapter extends FragmentPagerAdapter {


		AllFragment fragment1;
		PaidFragment fragment2;
		UnPaidFragment fragment3;

		public myPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					fragment1 = new AllFragment();
					return fragment1;

				case 1:
					fragment2 = new PaidFragment();
					return fragment2;
				case 2:
					fragment3 = new UnPaidFragment();
					return fragment3;
				default:
					return null;
			}
		}

		@Override
		public int getCount() {

			return title.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return title[position];
		}

	}




}
