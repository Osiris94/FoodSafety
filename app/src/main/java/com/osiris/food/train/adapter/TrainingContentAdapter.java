package com.osiris.food.train.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.ApplyRecords;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private MyItemClickListener myItemClickListener;

	private List<ApplyRecords.DataBean> dataList = new ArrayList<>();


	public TrainingContentAdapter(List<ApplyRecords.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<ApplyRecords.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_records, parent, false);//解决宽度不能铺满

		return new TrainRecordHolder(view, myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((TrainRecordHolder) holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}


	class TrainRecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


		@BindView(R.id.tv_train_name)
		TextView tv_train_name;
		@BindView(R.id.tv_pass)
		TextView tv_pass;
		@BindView(R.id.tv_type)
		TextView tv_type;
		@BindView(R.id.tv_time)
		TextView tv_time;


		private MyItemClickListener myItemClickListener;

		public TrainRecordHolder(View itemView, MyItemClickListener myItemClickListener) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			this.myItemClickListener = myItemClickListener;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (null != myItemClickListener) {
				myItemClickListener.onItemClick(itemView, getLayoutPosition());
			}
		}

		public void bindData(ApplyRecords.DataBean data) {

			tv_train_name.setText(data.getCert());
			tv_pass.setText(data.getPass());
			tv_type.setText(data.getType());
			tv_time.setText(data.getCreated_at());


		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener) {
		this.myItemClickListener = myItemClickListener;
	}
}


