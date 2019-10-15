package com.osiris.food.payment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class PayActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_pay_count)
	TextView tv_pay_count;
	@BindView(R.id.tv_pay_order_id)
	TextView tv_pay_order_id;
	@BindView(R.id.tv_course_name)
	TextView tv_course_name;
	@BindView(R.id.tv_pay)
	TextView tv_pay;
	@BindView(R.id.checkbox_ali)
	CheckBox checkbox_ali;
	@BindView(R.id.checkbox_wechat)
	CheckBox checkbox_wechat;

	private String name;
	private int subject_id;
	private String price;
	private String subject_no;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_payment;
	}

	@Override
	public void init() {

		name = getIntent().getStringExtra("name");
		price = getIntent().getStringExtra("price");
		subject_no = getIntent().getStringExtra("subject_no");
		subject_id = getIntent().getIntExtra("subject_id",0);

		tv_pay_order_id.setText(subject_no);
		tv_course_name.setText(name);
		tv_pay_count.setText(price);
		tv_pay.setText(String.format(getResources().getString(R.string.txt_payment_count),price));

		tv_title.setText(getString(R.string.txt_payment_confirm));
		checkbox_ali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				if (isChecked){
					checkbox_wechat.setChecked(false);
				}


			}
		});
		checkbox_wechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				if (isChecked){
					checkbox_ali.setChecked(false);
				}


			}
		});

	}


	@OnClick({R.id.rl_back,R.id.tv_confirm_pay})
	void onClick(View v){
		switch (v.getId()){
			case R.id.rl_back:
				finish();
				break;
			case R.id.tv_confirm_pay:
				uploadData();
				break;
		}
	}



	private void uploadData(){

		String url = ApiRequestTag.API_HOST + "/api/v1/report/order";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("train_id",String.valueOf(subject_id));
		//paramMap.put("train_id",String.valueOf(1));



		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf ---------" + successResult);
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200 && json.get("status").getAsString().equals("success")){
					finish();

				}
			}


			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});



	}



}
