package com.osiris.food.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.Notification;
import com.osiris.food.view.widget.CustomTextView;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<Notification.DataBean> dataList = new ArrayList<>();


	public NotificationAdapter(List<Notification.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<Notification.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent,false);//解决宽度不能铺满

		return new NotificationHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((NotificationHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class NotificationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_notification)
		CustomTextView tv_notification;
		@BindView(R.id.view_top)
		View view_top;
		@BindView(R.id.tv_date)
		TextView tv_date;
		@BindView(R.id.tv_notification_type)
		TextView tv_notification_type;


		private MyItemClickListener myItemClickListener;

		public NotificationHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(Notification.DataBean data){

			if (getLayoutPosition() ==0){
				view_top.setVisibility(View.GONE);
			}else {
				view_top.setVisibility(View.VISIBLE);
			}
			tv_notification.setText(data.getSubject());
			tv_date.setText(data.getCreated_at());
			tv_notification_type.setText(data.getType());
		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


