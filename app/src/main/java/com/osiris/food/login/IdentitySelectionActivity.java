package com.osiris.food.login;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.login.adapter.IdentitySelectAdapter;
import com.osiris.food.model.CloseEvent;
import com.osiris.food.model.IdentitySelect;
import com.osiris.food.view.widget.MyItemClickListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class IdentitySelectionActivity extends BaseActivity {


	@BindView(R.id.rv_data)
	RecyclerView rv_data;
	private List<IdentitySelect> dataList = new ArrayList<>();
	private IdentitySelectAdapter dataAdapter = new IdentitySelectAdapter(dataList);
	private int positionId  = -1;


	@Override
	public int getLayoutResId() {
		return R.layout.activity_select_identity;
	}

	@Override
	public void init() {
		dataList.add(new IdentitySelect(1,"监管人员",false));
		dataList.add(new IdentitySelect(2,"食品安全协管员",false));
		dataList.add(new IdentitySelect(3,"食品安全管理员",false));
		dataList.add(new IdentitySelect(4,"食品经营业主",false));
		dataList.add(new IdentitySelect(5,"食品从业人员",false));


		rv_data.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				for (IdentitySelect identitySelect:dataList){
					identitySelect.setSelect(false);
				}
				dataList.get(position).setSelect(true);
				dataAdapter.notifyDataSetChanged();
				positionId = dataList.get(position).getId();
			}
		});

	}



	@OnClick({R.id.tv_cancel,R.id.tv_confirm,R.id.rl_back})
	void onClick(View v){
		switch (v.getId()){
			case R.id.tv_confirm:
				if (positionId<0){
					Toast.makeText(this,"请选择身份",Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent(this,RegistActivity.class);
				intent.putExtra("identity",positionId);
				startActivity(intent);
				break;
			case R.id.tv_cancel:
				finish();
				break;
			case R.id.rl_back:
				finish();
				break;
		}

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onGetMessage(CloseEvent closeEvent) {
		finish();

	}


}
