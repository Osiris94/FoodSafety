package com.osiris.food.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.mine.adapter.CerAdapter;
import com.osiris.food.model.Certification;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCertificationActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;
	@BindView(R.id.tv_nodata)
	TextView tvNodata;

	private List<Certification.DataBean> dataList = new ArrayList<>();
	private CerAdapter dataAdapter = new CerAdapter(dataList);


	@Override
	public int getLayoutResId() {
		return R.layout.activity_certification;
	}

	@Override
	public void init() {


		tv_title.setText(getString(R.string.txt_title_mine_certificate));


		rv_data.setLayoutManager(new GridLayoutManager(this, 2));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		getCertification();

	}


	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
		}
	}


	private void getCertification(){

		String url = ApiRequestTag.API_HOST + "/api/v1/users/certificate";

		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					Certification.DataBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonArray(), Certification.DataBean[].class);
					if (dataList.size()>0) {
						dataList.clear();
					}
					dataList.addAll(Arrays.asList(data));
					dataAdapter.notifyDataSetChanged();

					if (dataList.size() == 0){
						tvNodata.setVisibility(View.VISIBLE);
					}

				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});


	}


}
