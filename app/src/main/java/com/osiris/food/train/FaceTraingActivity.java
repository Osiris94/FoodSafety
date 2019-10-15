package com.osiris.food.train;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FaceTraingActivity extends BaseActivity {



	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_face_traing;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.training_face_title));



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
