package com.osiris.food.login;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PolicyActivity extends BaseActivity {

	@BindView(R.id.tv_agree)
	TextView tv_agree;
	@BindView(R.id.tv_not_agree)
	TextView tv_not_agree;
	@BindView(R.id.checkbox_policy)
	CheckBox checkbox_policy;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_policy;
	}

	@Override
	public void init() {





	}



	@OnClick({R.id.tv_not_agree,R.id.tv_agree})
	void onClick(View v){
		switch (v.getId()){
			case R.id.tv_not_agree:

				if (checkbox_policy.isChecked()){
					finish();
				}

				break;
			case R.id.tv_agree:
				if (checkbox_policy.isChecked()){
					finish();
				}
				break;
		}
	}



}
