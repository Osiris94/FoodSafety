package com.osiris.food.login.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.IdentitySelect;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IdentitySelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<IdentitySelect> dataList = new ArrayList<>();


	public IdentitySelectAdapter(List<IdentitySelect> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<IdentitySelect> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_identity, parent,false);//解决宽度不能铺满

		return new IdentitySelectHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((IdentitySelectHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class IdentitySelectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_identity)
		TextView tv_identity;
		@BindView(R.id.checkbox_select)
		CheckBox checkbox_select;



		private MyItemClickListener myItemClickListener;

		public IdentitySelectHolder(View itemView,MyItemClickListener myItemClickListener) {
			super(itemView);
			ButterKnife.bind(this,itemView);
			this.myItemClickListener = myItemClickListener;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (null != myItemClickListener){
				myItemClickListener.onItemClick(itemView,getLayoutPosition());
			}
		}

		public void bindData(IdentitySelect data){


			tv_identity.setText(data.getName());
			if (data.isSelect()){
				checkbox_select.setChecked(true);
			}else {
				checkbox_select.setChecked(false);
			}
		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


