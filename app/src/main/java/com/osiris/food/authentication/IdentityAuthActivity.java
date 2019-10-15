package com.osiris.food.authentication;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class IdentityAuthActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.linear_content_commit)
	LinearLayout linear_content_commit;
	@BindView(R.id.linear_commit_sucess)
	LinearLayout linear_commit_sucess;
	@BindView(R.id.linear_page)
	LinearLayout linear_page;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_identity_auth;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.txt_totle_auth));

	}



	@OnClick({R.id.rl_back,R.id.btn_confirm,R.id.btn_return})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
			case R.id.btn_confirm:
				tv_title.setText(getString(R.string.txt_commit_auth));
				linear_content_commit.setVisibility(View.GONE);
				linear_commit_sucess.setVisibility(View.VISIBLE);
				linear_page.setBackgroundColor(getResources().getColor(R.color.gray_background_245));
				break;
			case R.id.btn_return:


				break;
		}
	}


}
