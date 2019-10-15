package com.osiris.food.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeInfoActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.edit_change)
	EditText editChange;
	@BindView(R.id.rl_confirm)
	RelativeLayout rlConfirm;
	@BindView(R.id.tv_change)
	TextView tv_change;

	public static final int CHANGE_INFO_NAME = 1;
	public static final int CHANGE_INFO_GENDA = CHANGE_INFO_NAME + 1;
	public static final int CHANGE_INFO_BIRTHDAY = CHANGE_INFO_GENDA + 1;
	public static final int CHANGE_INFO_PHONE = CHANGE_INFO_BIRTHDAY+1;

	private int type = 0;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_change_info;
	}

	@Override
	public void init() {

		type = getIntent().getIntExtra("type",0);
		switch (type){
			case CHANGE_INFO_NAME:
				tv_change.setText("修改姓名：");
				break;
			case CHANGE_INFO_GENDA:
				tv_change.setText("修改性别：");
				break;
			case CHANGE_INFO_BIRTHDAY:
				tv_change.setText("修改出生日期：");
				break;
			case CHANGE_INFO_PHONE:
				tv_change.setText("修改手机号码：");
				break;
		}


	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}

	@OnClick({R.id.rl_back, R.id.rl_confirm})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.rl_confirm:


				changeInfo();


				break;
		}
	}

	private void changeInfo() {


		String url = ApiRequestTag.API_HOST + "/api/v1/users/update";

		Map<String, String> paramMap = new HashMap<>();
		String param = "";
		switch (type){
			case CHANGE_INFO_NAME:
				param = "name";
				break;
			case CHANGE_INFO_GENDA:
				param = "gender";
				break;
			case CHANGE_INFO_BIRTHDAY:
				param = "birthday";
				break;
			case CHANGE_INFO_PHONE:
				param = "phone";
				break;
		}
		if (TextUtils.isEmpty(param))
			return;

		paramMap.put(param,editChange.getText().toString());
		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){
					switch (type){
						case CHANGE_INFO_NAME:
							GlobalParams.user_name = editChange.getText().toString();
							break;
						case CHANGE_INFO_GENDA:
							GlobalParams.gender = editChange.getText().toString();
							break;
						case CHANGE_INFO_BIRTHDAY:
							GlobalParams.birthday = editChange.getText().toString();
							break;
						case CHANGE_INFO_PHONE:
							GlobalParams.phone = editChange.getText().toString();
							break;
					}




				}
			}


			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});


	}
}
