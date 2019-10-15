package com.osiris.food.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.edt_pwd)
	EditText edtPwd;
	@BindView(R.id.tv_reset)
	TextView tvReset;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_reset_pwd;
	}

	@Override
	public void init() {

		tv_title.setText("忘记密码");


	}


	@OnClick({R.id.rl_back, R.id.tv_reset})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.tv_reset:
				resetPwd();
				break;

		}
	}

	//

	private void resetPwd() {

		String url = ApiRequestTag.API_HOST + "/api/v1/users/resetPwd";
		Map<String, String> paramMap = new HashMap<>();

		if (!TextUtils.isEmpty(edtPwd.getText())) {
			paramMap.put("phone", edtPwd.getText().toString());
		} else {
			Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
			return;
		}
		showLoadDialog();

		NetRequest.requestParamWithNoToken(url, ApiRequestTag.REQUEST_DATA,paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				cancelLoadDialog();
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200 && json.get("status").getAsString().equals("success")) {
					Toast.makeText(ResetPasswordActivity.this, "密码重置为初始密码123456", Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(ResetPasswordActivity.this, "密码重置失败", Toast.LENGTH_SHORT).show();
				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				cancelLoadDialog();
				Toast.makeText(ResetPasswordActivity.this, "密码重置失败", Toast.LENGTH_SHORT).show();
			}
		});

	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
