package com.osiris.food.login;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.CloseEvent;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.view.dialog.RegistCompleteDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class RegistActivity extends BaseActivity {

	@BindView(R.id.edt_name)
	EditText edt_name;
	@BindView(R.id.edt_sex)
	EditText edt_sex;
	@BindView(R.id.edt_phone)
	EditText edt_phone;
	@BindView(R.id.edt_document_num)
	EditText edt_document_num;
	@BindView(R.id.edt_company_name)
	EditText edt_company_name;

	private int positionId = -1;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_regist;
	}

	@Override
	public void init() {
		positionId = getIntent().getIntExtra("identity", -1);

	}

	@OnClick({R.id.btn_next, R.id.rl_back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_next:
				if (TextUtils.isEmpty(edt_name.getText().toString()) || TextUtils.isEmpty(edt_sex.getText().toString()) ||
						TextUtils.isEmpty(edt_phone.getText().toString()) ||
						TextUtils.isEmpty(edt_document_num.getText().toString())
						||TextUtils.isEmpty(edt_company_name.getText().toString())
						) {
					Toast.makeText(this, "请填写完整", Toast.LENGTH_SHORT).show();
					return;
				}else {
					registApp();
				}
				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}


	private void registApp() {

		String url = ApiRequestTag.API_HOST + "/api/v1/signup";
		Map<String, String> paramMap = new HashMap<>();


		paramMap.put("name", edt_name.getText().toString());
		paramMap.put("gender", edt_sex.getText().toString());
		paramMap.put("phone", edt_phone.getText().toString());

		paramMap.put("id_type", "1");
		paramMap.put("education", "5");

		/*if (document.contains("身份证")) {
			paramMap.put("id_type", "1");
		} else if (document.contains("军官证")) {
			paramMap.put("id_type", "2");
		} else {
			paramMap.put("id_type", "3");
		}
		if (eduction.contains("小学")) {
			paramMap.put("education", "1");
		} else if (eduction.contains("初中")) {
			paramMap.put("education", "2");
		} else if (eduction.contains("高中")) {
			paramMap.put("education", "3");
		} else if (eduction.contains("专科")) {
			paramMap.put("education", "4");
		} else if (eduction.contains("本科")) {
			paramMap.put("education", "5");
		} else if (eduction.contains("硕士")) {
			paramMap.put("education", "6");
		} else if (eduction.contains("博士")) {
			paramMap.put("education", "7");
		}*/

		paramMap.put("id_no", edt_document_num.getText().toString());
		paramMap.put("email", "339998877@qq.com");
		paramMap.put("industry_type", "食品安全");
		paramMap.put("position", "管理员");
		paramMap.put("company", edt_company_name.getText().toString());
		paramMap.put("company_tel", "0512-8988988282");
		paramMap.put("province", "1");
		paramMap.put("city","2");
		paramMap.put("address", "江苏省泰州市");
		//paramMap.put("district", cityId+"01");
		paramMap.put("password", "123456");
		paramMap.put("type", String.valueOf(positionId));


		Set keys = paramMap.keySet();
		for (Object key : keys) {
			System.out.print(key + "=" + paramMap.get(key));
		}
		System.out.println("\n----------------------");
		//3\***
		Set<Map.Entry<String, String>> ms = paramMap.entrySet();
		for (Map.Entry entry : ms) {
			System.out.print(entry.getKey() + "=" + entry.getValue());
		}


		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf  sdsdd :" + successResult);
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					showRegistComplete();
				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf code:" + code);
			}
		});


	}

	private void showRegistComplete() {
		RegistCompleteDialog.Builder preventBuilder = new RegistCompleteDialog.Builder(this);
		preventBuilder.setPositiveButton(new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
				postEvent(new CloseEvent());
				finish();
			}
		});

		preventBuilder.create().show();
	}


	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onGetMessage(CloseEvent closeEvent) {
		finish();

	}


}
