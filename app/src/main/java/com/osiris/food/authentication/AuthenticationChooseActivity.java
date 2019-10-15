package com.osiris.food.authentication;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthenticationChooseActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_authentication_choose;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.txt_choose_auth));

	}


	@OnClick({R.id.rl_back, R.id.rl_choose_third, R.id.rl_choose_card})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.rl_choose_third:
				break;
			case R.id.rl_choose_card:
				break;
		}
	}
}
