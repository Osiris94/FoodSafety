package com.osiris.food.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.edu.EduInfoActivity;
import com.osiris.food.model.StudyCourse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ApplyInfFragment extends BaseFragment {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;

	Unbinder unbinder;

	@Override
	protected int setLayout() {
		return R.layout.fragmnent_apply;
	}

	@Override
	protected void initView() {
		tv_title.setText("申请食品安全管理员证");
		rl_back.setVisibility(View.GONE);



	}

	@Override
	protected void initData() {

	}

	@OnClick({R.id.tv_continue_edu})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_continue_edu:
				Intent intent = new Intent(getActivity(), EduInfoActivity.class);
				startActivity(intent);
				break;

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder = ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}


	private List<StudyCourse> dataList = new ArrayList<>();






}
