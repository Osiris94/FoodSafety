package com.osiris.food.edu.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.EduList;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyEduAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private MyItemClickListener myItemClickListener;

	private List<EduList.DataBean> dataList = new ArrayList<>();


	public ApplyEduAdapter(List<EduList.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<EduList.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apply_edu, parent, false);//解决宽度不能铺满

		return new ApplyEduHolder(view, myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((ApplyEduHolder) holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}


	class ApplyEduHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


		@BindView(R.id.tv_apply)
		TextView tv_apply;
		@BindView(R.id.tv_project_id)
		TextView tv_project_id;
		@BindView(R.id.tv_level)
		TextView tv_level;
		@BindView(R.id.tv_price)
		TextView tv_price;
		@BindView(R.id.tv_title)
		TextView tv_title;



		private MyItemClickListener myItemClickListener;

		public ApplyEduHolder(View itemView, MyItemClickListener myItemClickListener) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			this.myItemClickListener = myItemClickListener;
			tv_apply.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (null != myItemClickListener) {
				myItemClickListener.onItemClick(itemView, getLayoutPosition());
			}
		}

		public void bindData(EduList.DataBean data) {

			if (!TextUtils.isEmpty(data.getName())){
				tv_title.setText(data.getName());
			}
			if (!TextUtils.isEmpty(data.getTrain_no())){
				tv_project_id.setText("项目编号：" + data.getTrain_no());
			}
			if (!TextUtils.isEmpty(data.getPrice())){
				tv_price.setText(data.getPrice());
			}

			if (data.getOrder_num() == 0){
				tv_apply.setText("申请");
			}else {
				tv_apply.setText("已申请");
			}
			//if (data.getLevel())

		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener) {
		this.myItemClickListener = myItemClickListener;
	}
}


