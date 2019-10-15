package com.osiris.food.edu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseTypeActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.rd_one)
	RadioButton rdOne;
	@BindView(R.id.rd_two)
	RadioButton rdTwo;
	@BindView(R.id.rd_three)
	RadioButton rdThree;
	@BindView(R.id.rd_group)
	RadioGroup rdGroup;
	private int type = 0;
	private int selectType = 1;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_choose_type;
	}

	@Override
	public void init() {

		type = getIntent().getIntExtra("type", 1);
		if (type == 0) {
			rdTwo.setVisibility(View.GONE);
			rdThree.setVisibility(View.GONE);
			tvTitle.setText("选择机构");
			rdOne.setText("泰州市海陵区食品监督管理局");

		} else {
			tvTitle.setText("选择就业");
			rdOne.setText("餐饮行业");
			rdTwo.setText("生产企业");
			rdThree.setText("流通领域");

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}

	@OnClick({R.id.rl_back, R.id.rd_one, R.id.rd_two, R.id.rd_three,R.id.tv_confirm})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.rl_back:
//				Intent intent1 = new Intent();
//				intent1.putExtra("result", "");
				finish();
				break;
			case R.id.rd_one:
				selectType = 1;
				break;
			case R.id.rd_two:
				selectType = 2;
				break;
			case R.id.rd_three:
				selectType = 3;
				break;
			case R.id.tv_confirm:
				Intent intent = new Intent();
				//把返回数据存入Intent
				if (selectType ==1){
					intent.putExtra("result", rdOne.getText().toString());
				}else if (selectType == 2){
					intent.putExtra("result", rdTwo.getText().toString());
				}else {
					intent.putExtra("result", rdThree.getText().toString());
				}
				intent.putExtra("type",selectType);
				setResult(RESULT_OK, intent);
				finish();
				break;
		}
	}
}
