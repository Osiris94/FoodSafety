package com.osiris.food.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.ScoreDetailAdapter;
import com.osiris.food.model.ScoreDetail;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class ScoreDetailActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<ScoreDetail.DataBean> dataList = new ArrayList<>();
	private ScoreDetailAdapter dataAdapter = new ScoreDetailAdapter(dataList);

	@Override
	public int getLayoutResId() {
		return R.layout.activity_score_detail;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.score_detail));



		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		getTodayScoreDetail();

	}

	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
		}
	}


	// /api/v1/tasks/record

	private void getTodayScoreDetail() {

		String url = ApiRequestTag.API_HOST + "/api/v1/tasks/record";
		LogUtils.d("zkf url:" + url);

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {

					ScoreDetail.DataBean[] dataBeans = JsonUtils.fromJson(json.get("data").getAsJsonArray(),ScoreDetail.DataBean[].class);
					dataList.addAll(Arrays.asList(dataBeans));
					dataAdapter.notifyDataSetChanged();

				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf code:" + code);

			}
		});

	}


}
