package com.osiris.food.train;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.TrainRecord;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.train.adapter.TrainRecordAdapter;
import com.osiris.food.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TrainRecordActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;


	private List<TrainRecord.DataBean> dataList = new ArrayList<>();
	private TrainRecordAdapter dataAdapter = new TrainRecordAdapter(dataList);

	@Override
	public int getLayoutResId() {
		return R.layout.activity_train_reccord;
	}

	@Override
	public void init() {



		tv_title.setText(getString(R.string.txt_train_record));


		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		getTrainRecords();

	}



	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
		}
	}


	private void getTrainRecords(){

		String url = ApiRequestTag.API_HOST + "/api/v1/lessons/record";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					TrainRecord.DataBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonArray(), TrainRecord.DataBean[].class);
					if (dataList.size()>0) {
						dataList.clear();
					}
					dataList.addAll(Arrays.asList(data));
					dataAdapter.notifyDataSetChanged();

				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});


	}


}
