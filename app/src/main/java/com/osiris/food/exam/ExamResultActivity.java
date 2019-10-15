package com.osiris.food.exam;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamResultActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.rl_submit)
	RelativeLayout rlSubmit;
	@BindView(R.id.title)
	RelativeLayout title;
	@BindView(R.id.tv_exam_result)
	TextView tvExamResult;
	@BindView(R.id.tv_exam_score)
	TextView tvExamScore;

	private int score = 0;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_exam_result;
	}

	@Override
	public void init() {
		String scoreTemp=  getIntent().getStringExtra("exam_score");
		if (!TextUtils.isEmpty(scoreTemp)){
			double scoreDouble = Double.parseDouble(scoreTemp);
			score = (new Double(scoreDouble)).intValue();
			if (score<60){
				tvExamResult.setText("考试未通过");
			}else {
				tvExamResult.setText("考试已通过");
			}
			tvExamScore.setText(String.valueOf(score));
			tvTitle.setText("考试结果");

		}

	}

	@OnClick({R.id.rl_back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;

		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}

}
