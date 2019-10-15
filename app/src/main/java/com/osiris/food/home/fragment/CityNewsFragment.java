package com.osiris.food.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.FragmentChangeEvent;
import com.osiris.food.home.ContentDetailActivity;
import com.osiris.food.home.adapter.CityNewsAdapter;
import com.osiris.food.model.PolicyList;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.IBackInterface;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

import static com.osiris.food.home.MenuActivity.FRAGMENT_HOME;
import static com.osiris.food.network.ApiRequestTag.REQUEST_DATA;

public class CityNewsFragment extends BaseFragment {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;


	private List<PolicyList.DataBeanX.DataBean> dataList = new ArrayList<>();
	private CityNewsAdapter dataAdapter = new CityNewsAdapter(dataList);
	private IBackInterface backInterface;


	@OnClick({R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				postEvent(new FragmentChangeEvent(FRAGMENT_HOME));
				break;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		getData();
	}

	@Override
	protected int setLayout() {
		return R.layout.activity_city_news;
	}

	@Override
	protected void initView() {
		backInterface = (IBackInterface)getActivity();
		backInterface.setSelectedFragment(this);//将fragment传递到Activity中

		tv_title.setText(getString(R.string.city_dynamic));

		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(getActivity(), ContentDetailActivity.class);
				intent.putExtra("id",dataList.get(position).getId());
				startActivity(intent);
			}
		});
	}

	@Override
	protected void initData() {

	}
	public boolean onBackPressed() {
		postEvent(new FragmentChangeEvent(FRAGMENT_HOME));
		LogUtils.d("zkf onBackPressed");
		return true;
	}
	private void getData() {
		String url = ApiRequestTag.API_HOST + "/api/v1/contents";

		Map<String, String> paramMap = new HashMap<>();

		paramMap.put("category_id","2");
		showLoadDialog();
//		paramMap.put("")
		NetRequest.requestParamWithToken(url, REQUEST_DATA,paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					PolicyList.DataBeanX.DataBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonObject().get("data").getAsJsonArray(), PolicyList.DataBeanX.DataBean[].class);
					if (dataList.size()>0) {
						dataList.clear();
					}

					dataList.addAll(Arrays.asList(data));
					dataAdapter.notifyDataSetChanged();
				}
				cancelLoadDialog();
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf code :" + code);
				cancelLoadDialog();
			}
		});



	}


}
