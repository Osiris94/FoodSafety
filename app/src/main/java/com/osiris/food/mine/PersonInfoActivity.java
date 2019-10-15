package com.osiris.food.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.view.CircleImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

import static com.osiris.food.mine.ChangeInfoActivity.CHANGE_INFO_BIRTHDAY;
import static com.osiris.food.mine.ChangeInfoActivity.CHANGE_INFO_GENDA;
import static com.osiris.food.mine.ChangeInfoActivity.CHANGE_INFO_NAME;
import static com.osiris.food.mine.ChangeInfoActivity.CHANGE_INFO_PHONE;

public class PersonInfoActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.iv_avatar)
	CircleImageView iv_avatar;
	@BindView(R.id.tv_nick_name)
	TextView tv_nick_name;
	@BindView(R.id.tv_sex)
	TextView tv_sex;
	@BindView(R.id.tv_birthday)
	TextView tv_birthday;
	@BindView(R.id.tv_study_num)
	TextView tv_study_num;
	@BindView(R.id.tv_phone_num)
	TextView tv_phone_num;
	@BindView(R.id.tv_location)
	TextView tv_location;
	@BindView(R.id.tv_work_status)
	TextView tv_work_status;
	@BindView(R.id.tv_pseronal_check)
	TextView tv_pseronal_check;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_personal_info;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.personal_info));
		if (!TextUtils.isEmpty(GlobalParams.birthday))
			tv_birthday.setText(GlobalParams.birthday);
		if (!TextUtils.isEmpty(GlobalParams.gender))
			tv_sex.setText(GlobalParams.gender);
		if (!TextUtils.isEmpty(GlobalParams.user_name))
			tv_nick_name.setText(GlobalParams.user_name);
		if (!TextUtils.isEmpty(GlobalParams.phone))
			tv_phone_num.setText(GlobalParams.phone);
		if (!TextUtils.isEmpty(GlobalParams.address))
			tv_location.setText(GlobalParams.address);

		tv_study_num.setText(String.valueOf(GlobalParams.identity_card_type));

		if (!TextUtils.isEmpty(GlobalParams.avatar)) {

			Picasso.with(this)
					.load(GlobalParams.avatar)
					.into(iv_avatar);
		}


	}


	@Override
	protected void onResume() {
		super.onResume();
		if (!TextUtils.isEmpty(GlobalParams.birthday))
			tv_birthday.setText(GlobalParams.birthday);
		if (!TextUtils.isEmpty(GlobalParams.gender))
			tv_sex.setText(GlobalParams.gender);
		if (!TextUtils.isEmpty(GlobalParams.user_name))
			tv_nick_name.setText(GlobalParams.user_name);
		if (!TextUtils.isEmpty(GlobalParams.phone))
			tv_phone_num.setText(GlobalParams.phone);
		if (!TextUtils.isEmpty(GlobalParams.address))
			tv_location.setText(GlobalParams.address);

		tv_study_num.setText(String.valueOf(GlobalParams.identity_card_type));

		if (!TextUtils.isEmpty(GlobalParams.avatar)) {
			Picasso.with(this)
					.load(GlobalParams.avatar)
					.into(iv_avatar);
		}
	}

	@OnClick({R.id.rl_back, R.id.iv_avatar, R.id.rl_nick_name,
			R.id.rl_sex, R.id.rl_birthday, R.id.rl_study_num, R.id.rl_phone,
			R.id.rl_location, R.id.rl_work_status, R.id.rl_personal_check, R.id.rl_qr_card})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.iv_avatar:
				break;
			case R.id.rl_nick_name:
				Intent intent = new Intent(this, ChangeInfoActivity.class);
				intent.putExtra("type", CHANGE_INFO_NAME);
				startActivity(intent);
				break;
			case R.id.rl_sex:
				Intent intent2 = new Intent(this, ChangeInfoActivity.class);
				intent2.putExtra("type", CHANGE_INFO_GENDA);
				startActivity(intent2);
				break;
			case R.id.rl_birthday:
				Intent intent3 = new Intent(this, ChangeInfoActivity.class);
				intent3.putExtra("type", CHANGE_INFO_BIRTHDAY);
				startActivity(intent3);
				break;
			case R.id.rl_study_num:
				break;
			case R.id.rl_phone:
				Intent intent4 = new Intent(this, ChangeInfoActivity.class);
				intent4.putExtra("type", CHANGE_INFO_PHONE);
				startActivity(intent4);
				break;
			case R.id.rl_location:
				break;
			case R.id.rl_work_status:
				break;
			case R.id.rl_personal_check:
				break;
			case R.id.rl_qr_card:
				Intent intent5 = new Intent(this,QRCodeActivity.class);
				startActivity(intent5);
				break;
		}
	}
}
