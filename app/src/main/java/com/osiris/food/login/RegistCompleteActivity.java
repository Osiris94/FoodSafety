package com.osiris.food.login;

import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.CloseEvent;
import com.osiris.food.model.ProvinceDetail;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.dialog.RegistCompleteDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class RegistCompleteActivity extends BaseActivity {


	@BindView(R.id.edt_industry)
	EditText edt_industry;
	@BindView(R.id.edt_post)
	EditText edt_post;
	@BindView(R.id.edt_company_name)
	EditText edt_company_name;
	@BindView(R.id.edt_company_phone)
	EditText edt_company_phone;
	@BindView(R.id.edt_company_location)
	EditText edt_company_location;
	@BindView(R.id.edt_company_detail_location)
	EditText edt_company_detail_location;
	private String name;
	private String sex;
	private String phone;
	private String document;
	private String document_num;
	private String mail;
	private String eduction;
	private int princeId;
	private int cityId;
	private int positionId = -1;

	private List<ProvinceDetail.DataBean> dataList = new ArrayList<>();
	private List<ProvinceDetail.DataBean> dataCityList = new ArrayList<>();


	@Override
	public int getLayoutResId() {
		return R.layout.activity_complete_regist;
	}

	@Override
	public void init() {
		name = getIntent().getStringExtra("name");
		sex = getIntent().getStringExtra("sex");
		phone = getIntent().getStringExtra("phone");
		document = getIntent().getStringExtra("document");
		document_num = getIntent().getStringExtra("document_num");
		mail = getIntent().getStringExtra("mail");
		eduction = getIntent().getStringExtra("eduction");
		positionId = getIntent().getIntExtra("identity",-1);

		getProvinceId();

	}

	@OnClick({R.id.btn_regist, R.id.rl_back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_regist:
				LogUtils.d("zkf edt_company_location.getText().toString():" +edt_company_location.getText().toString());
				for (ProvinceDetail.DataBean dataBean:dataList){
					LogUtils.d("zkf dataBean.getName():" +dataBean.getName());
					if (edt_company_location.getText().toString().contains(dataBean.getName())){
						princeId = dataBean.getId();
						getCityId(princeId);
					}
				}
				break;
			case R.id.rl_back:
				finish();
				break;
		}
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

	private Map<String, String> paramMap = new HashMap<>();
	private void registApp() {

		String url = ApiRequestTag.API_HOST + "/api/v1/signup";


		paramMap.put("name", name);
		paramMap.put("gender", sex);
		paramMap.put("phone", phone);
		if (document.contains("身份证")){
			paramMap.put("id_type", "1");
		}else if (document.contains("军官证")){
			paramMap.put("id_type", "2");
		}else {
			paramMap.put("id_type", "3");
		}
		if (eduction.contains("小学")){
			paramMap.put("education","1");
		}else if (eduction.contains("初中")){
			paramMap.put("education","2");
		}else if (eduction.contains("高中")){
			paramMap.put("education","3");
		}else if (eduction.contains("专科")){
			paramMap.put("education","4");
		}else if (eduction.contains("本科")){
			paramMap.put("education","5");
		}else if (eduction.contains("硕士")){
			paramMap.put("education","6");
		}else if (eduction.contains("博士")){
			paramMap.put("education","7");
		}

		paramMap.put("id_no", document_num);
		paramMap.put("email", mail);
		paramMap.put("industry_type", edt_industry.getText().toString());
		paramMap.put("position", edt_post.getText().toString());
		paramMap.put("company", edt_company_name.getText().toString());
		paramMap.put("company_tel", edt_company_phone.getText().toString());
		paramMap.put("province", String.valueOf(princeId));
		paramMap.put("city", String.valueOf(cityId));
		paramMap.put("address",edt_company_detail_location.getText().toString());
		//paramMap.put("district", cityId+"01");
		paramMap.put("password", "123456");
		paramMap.put("type",String.valueOf(positionId));


		Set keys = paramMap.keySet();
		for (Object key : keys) {
			System.out.print(key+"="+paramMap.get(key));
		}
		System.out.println("\n----------------------");
		//3\***
		Set<Map.Entry<String, String>> ms =paramMap.entrySet();
		for (Map.Entry entry : ms) {
			System.out.print(entry.getKey()+"="+entry.getValue());
		}


		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf  sdsdd :" +successResult);
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 201){
					showRegistComplete();
				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf code:" + code);
			}
		});




	}


	private void getCityId(int id){
		String url = ApiRequestTag.API_HOST + "/api/v1/regions";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("pid",String.valueOf(id));

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA,paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){

					ProvinceDetail.DataBean[] dataBeans = JsonUtils.fromJson(json.get("data").getAsJsonArray(),ProvinceDetail.DataBean[].class);
					dataCityList.addAll(Arrays.asList(dataBeans));

					for (ProvinceDetail.DataBean dataBean:dataCityList){
						if (edt_company_location.getText().toString().contains(dataBean.getName())){
							cityId = dataBean.getId();
							registApp();
						}
					}


					LogUtils.d("zkf data list size:" + dataList.size());


				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});

	}



	private void getProvinceId(){


		String url = ApiRequestTag.API_HOST + "/api/v1/regions";

		Map<String, String> paramMap = new HashMap<>();

		paramMap.put("pid","0");

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA,paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200){

					ProvinceDetail.DataBean[] dataBeans = JsonUtils.fromJson(json.get("data").getAsJsonArray(),ProvinceDetail.DataBean[].class);
					dataList.addAll(Arrays.asList(dataBeans));
					LogUtils.d("zkf data list size:" + dataList.size());

				}


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {

			}
		});

	}




}
