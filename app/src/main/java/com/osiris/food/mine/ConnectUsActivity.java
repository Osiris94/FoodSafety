package com.osiris.food.mine;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ConnectUsActivity extends BaseActivity {

	@BindView(R.id.tv_hotline)
	TextView tv_hotline;
	@BindView(R.id.tv_home_page)
	TextView tv_home_page;
	@BindView(R.id.tv_location)
	TextView tv_location;
	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;



	@Override
	public int getLayoutResId() {
		return R.layout.activity_connect_us;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.txt_title_mine_contract));

	}



	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
		}
	}


}
