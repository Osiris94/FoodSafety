package com.osiris.food.train.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.TrainRecord;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<TrainRecord.DataBean> dataList = new ArrayList<>();


	public TrainRecordAdapter(List<TrainRecord.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<TrainRecord.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_record, parent,false);//解决宽度不能铺满

		return new TrainRecordHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((TrainRecordHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class TrainRecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_train_name)
		TextView tv_train_name;
		@BindView(R.id.tv_start_time)
		TextView tv_start_time;
		@BindView(R.id.tv_term)
		TextView tv_term;



		private MyItemClickListener myItemClickListener;

		public TrainRecordHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(TrainRecord.DataBean data){
			tv_term.setText(data.getSchool_year());
			tv_train_name.setText(data.getName());
			if (!TextUtils.isEmpty(data.getCreated_at())&& data.getCreated_at().length()>10){
				tv_start_time.setText(data.getCreated_at().substring(0,10));
			}

		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


