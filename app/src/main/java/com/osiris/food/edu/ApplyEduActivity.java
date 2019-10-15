package com.osiris.food.edu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.edu.adapter.ApplyEduAdapter;
import com.osiris.food.model.EduList;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.payment.PayActivity;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.dialog.ContinueEduDialog;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class ApplyEduActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.rv_data)
	RecyclerView rv_data;
	@BindView(R.id.tv_org_type)
	TextView tv_org_type;
	@BindView(R.id.tv_job_type)
	TextView tv_job_type;

	private int selectType =1;
	private List<EduList.DataBean> dataList = new ArrayList<>();
	private ApplyEduAdapter dataAdapter = new ApplyEduAdapter(dataList);
	public static final int REQUEST_DATA = 1022;



	@Override
	public int getLayoutResId() {
		return R.layout.activity_apply_edu;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.apply_continue_eduction));


		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();

		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				if (TextUtils.isEmpty(tv_job_type.getText().toString())){
					showDialog();
					return;
				}
				if (dataList.get(position).getOrder_num() == 1){
					Toast.makeText(ApplyEduActivity.this,"此培训您已经购买",Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent(ApplyEduActivity.this, PayActivity.class);
				intent.putExtra("name",dataList.get(position).getName());
				intent.putExtra("price",dataList.get(position).getPrice());
				intent.putExtra("subject_no",dataList.get(position).getTrain_no());
				intent.putExtra("subject_id",dataList.get(position).getId());
				startActivity(intent);

				//showDialog();

			}
		});

		getEduList();


	}


	@OnClick({R.id.rl_back,R.id.rl_choose_work,R.id.rl_choose_job})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.rl_choose_work:
				Intent intent = new Intent(this, ChooseTypeActivity.class);
				intent.putExtra("type",0);
				startActivityForResult(intent,REQUEST_DATA);
				break;
			case R.id.rl_choose_job:
				Intent intent1 = new Intent(this, ChooseTypeActivity.class);
				intent1.putExtra("type",1);
				startActivityForResult(intent1,REQUEST_DATA);
				break;
		}
	}


	private void getEduList() {
		showLoadDialog();

		String url = ApiRequestTag.API_HOST + "/api/v1/trains";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("type",String.valueOf(selectType));


		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					EduList.DataBean[] dataBeans = JsonUtils.fromJson(json.get("data").getAsJsonArray(), EduList.DataBean[].class);
					if (dataBeans.length>0){
						dataList.clear();
					}
					dataList.addAll(Arrays.asList(dataBeans));
					dataAdapter.notifyDataSetChanged();
					cancelLoadDialog();

				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				cancelLoadDialog();
			}
		});

	}

	private void showDialog() {
		ContinueEduDialog.Builder preventBuilder = new ContinueEduDialog.Builder(this);
		preventBuilder.setPositiveButton(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});

		preventBuilder.create().show();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==REQUEST_DATA && null != data){
			String result = data.getExtras().getString("result");
			if (TextUtils.isEmpty(result)){
				return;
			}
			int type = data.getExtras().getInt("type");
			LogUtils.d("zkf result:" + result + "  type:" + type);
			if (type == 0){
				tv_org_type.setText(result);
			}else {
				tv_job_type.setText(result);
			}
			if (result.equals("餐饮行业")){
				selectType = 1;
			}else if (result.equals("生产企业")){
				selectType = 2;
			}if (result.equals("流通领域")){
				selectType = 3;
			}
			getEduList();
		}

	}


}
