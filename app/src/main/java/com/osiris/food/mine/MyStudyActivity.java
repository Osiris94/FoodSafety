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
import com.osiris.food.mine.adapter.MyStudyOtherAdapter;
import com.osiris.food.mine.adapter.StudyAdapter;
import com.osiris.food.model.MyStudy;
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

public class MyStudyActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;
	@BindView(R.id.rv_data_other)
	RecyclerView rv_data_other;

	private List<MyStudy.DataBean.VideoCourseBean> dataList = new ArrayList<>();
	private StudyAdapter dataAdapter = new StudyAdapter(dataList);


	private List<MyStudy.DataBean.PublicCourseBean> dataOtherList = new ArrayList<>();
	private MyStudyOtherAdapter dataOtherAdapter = new MyStudyOtherAdapter(dataOtherList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_study;
	}

	@Override
	public void init() {


		tv_title.setText(getString(R.string.txt_title_mine_study));

		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

		rv_data_other.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data_other.setAdapter(dataOtherAdapter);
		dataOtherAdapter.notifyDataSetChanged();


	//	getTodayScoreDetail();

	}

	@Override
	protected void onResume() {
		super.onResume();
		getStudySataDetail();
	}

	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
		}
	}




	private void getStudySataDetail() {

		String url = ApiRequestTag.API_HOST + "/api/v1/users/learning";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf adsdadd:" + successResult);
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					MyStudy.DataBean.VideoCourseBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonObject().get("video_course").getAsJsonArray(), MyStudy.DataBean.VideoCourseBean[].class);
					if (dataList.size()>0) {
						dataList.clear();
					}
					dataList.addAll(Arrays.asList(data));
					dataAdapter.notifyDataSetChanged();

					MyStudy.DataBean.PublicCourseBean[] dataOther = JsonUtils.fromJson(
							json.get("data").getAsJsonObject().get("major_course").getAsJsonArray(), MyStudy.DataBean.PublicCourseBean[].class);
					if (dataOtherList.size()>0) {
						dataOtherList.clear();
					}
					dataOtherList.addAll(Arrays.asList(dataOther));
					dataOtherAdapter.notifyDataSetChanged();

				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});

	}



}
