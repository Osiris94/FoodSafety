package com.osiris.food.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.ScoreRuleAdapter;
import com.osiris.food.model.ScoreDesc;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ScoreRuleActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;


	private List<ScoreDesc> dataList = new ArrayList<>();
	private ScoreRuleAdapter dataAdapter = new ScoreRuleAdapter(dataList);

	@Override
	public int getLayoutResId() {
		return R.layout.activity_score_rule;
	}

	@Override
	public void init() {

		tv_title.setText(getString(R.string.score_explam));


		dataList.add(new ScoreDesc("问：什么是学习积分？","答：学习积分是用户通过在“食品安全培训”学习平台完成指定行为获得的积分奖励。"));
		dataList.add(new ScoreDesc("问：谁能获得学习积分？","答：所有“食品安全培训”学习平台的注册用户。"));
		dataList.add(new ScoreDesc("问：如何获得学习积分？","答：完成指定的获得该行为对应的积分。"));




		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();


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
