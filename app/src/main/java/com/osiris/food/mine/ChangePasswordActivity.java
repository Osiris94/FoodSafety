package com.osiris.food.mine;

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

public class ChangePasswordActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.edt_pwd)
	EditText edtPwd;
	@BindView(R.id.edt_pwd_confirm)
	EditText edtPwdConfirm;
	@BindView(R.id.tv_reset)
	TextView tvReset;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_change_pwd;
	}

	@Override
	public void init() {
		tvTitle.setText("修改密码");

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}

	@OnClick({R.id.tv_reset,R.id.rl_back})
	public void onViewClicked(View v) {
		switch (v.getId()){
			case R.id.tv_reset:
				if (TextUtils.isEmpty(edtPwd.getText().toString())){
					Toast.makeText(ChangePasswordActivity.this,"请您输入密码",Toast.LENGTH_SHORT).show();
				}
				if (TextUtils.isEmpty(edtPwdConfirm.getText().toString())){
					Toast.makeText(ChangePasswordActivity.this,"请您确认密码",Toast.LENGTH_SHORT).show();
				}
				if (!edtPwd.getText().toString().equals(edtPwdConfirm.getText().toString())){
					Toast.makeText(ChangePasswordActivity.this,"两次密码输入不一致,请您重新确认密码",Toast.LENGTH_SHORT).show();
					return;
				}
				changePwd();
				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}


	private void changePwd() {
		showLoadDialog();

		String url = ApiRequestTag.API_HOST + "/api/v1/users/update";
		Map<String, String> paramMap = new HashMap<>();

		if (!TextUtils.isEmpty(edtPwd.getText())) {
			paramMap.put("password", edtPwd.getText().toString());
		} else {
			Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
			return;
		}

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA,paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				cancelLoadDialog();
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200 && json.get("status").getAsString().equals("success")) {
					Toast.makeText(ChangePasswordActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(ChangePasswordActivity.this,"密码修改失败",Toast.LENGTH_SHORT).show();

				}

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				cancelLoadDialog();
				Toast.makeText(ChangePasswordActivity.this,"密码修改失败",Toast.LENGTH_SHORT).show();
			}
		});

	}






}
