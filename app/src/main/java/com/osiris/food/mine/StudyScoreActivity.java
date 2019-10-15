package com.osiris.food.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.StudyScoreAdapter;
import com.osiris.food.model.ScoreList;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class StudyScoreActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.rl_top)
	RelativeLayout rlTop;
	@BindView(R.id.rv_data)
	RecyclerView rvData;
	@BindView(R.id.tv_notification)
	TextView tvNotification;
	@BindView(R.id.tv_socre)
	TextView tvScore;
	@BindView(R.id.tv_today_score)
	TextView tv_today_score;
	@BindView(R.id.tv_score_detail)
	TextView tv_score_detail;

	private List<ScoreList.DataBean> dataList = new ArrayList<>();
	private StudyScoreAdapter dataAdapter = new StudyScoreAdapter(dataList);

	@Override
	public int getLayoutResId() {
		return R.layout.activity_study_score;
	}

	@Override
	public void init() {

		tvNotification.setSelected(true);



		rvData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		rvData.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				LogUtils.d("zkf click");
			}
		});
		getTodayScoreDetail();
		getTotalScore();

		tv_score_detail.setVisibility(View.GONE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}



	@OnClick({R.id.tv_study_score, R.id.tv_score_detail,R.id.rl_back})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv_study_score:
				Intent intent = new Intent(this,ScoreDetailActivity.class);
				startActivity(intent);
				break;
			case R.id.tv_score_detail:
				Intent intent1 = new Intent(this,ScoreRuleActivity.class);
				startActivity(intent1);
				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}


	private void getTodayScoreDetail() {

		String url = ApiRequestTag.API_HOST + "/api/v1/tasks";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {

					ScoreList.DataBean[] dataBeans = JsonUtils.fromJson(json.get("data").getAsJsonArray(),ScoreList.DataBean[].class);
					dataList.addAll(Arrays.asList(dataBeans));
					dataAdapter.notifyDataSetChanged();
					int totalScore = 0;
					for (ScoreList.DataBean dataBean:dataList){
						totalScore = dataBean.getScore() + totalScore;
					}
					tv_today_score.setText("今日已获取"+totalScore+"积分");

				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf code:" + code);

			}
		});

	}



	private void getTotalScore() {

		String url = ApiRequestTag.API_HOST + "/api/v1/users/points";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					if (null != json.get("data").getAsJsonObject().get("user_point").getAsString()) {
						String score = json.get("data").getAsJsonObject().get("user_point").getAsString();
						tvScore.setText("已有积分" + score);
					}

				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
			}
		});

	}

}
