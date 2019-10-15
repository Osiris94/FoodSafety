package com.osiris.food.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.ScoreDetail;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<ScoreDetail.DataBean> dataList = new ArrayList<>();


	public ScoreDetailAdapter(List<ScoreDetail.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<ScoreDetail.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_detail, parent,false);//解决宽度不能铺满

		return new ScoreDetailHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((ScoreDetailHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class ScoreDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_score_type)
		TextView tv_score_type;
		@BindView(R.id.tv_score_date)
		TextView tv_score_date;
		@BindView(R.id.tv_score_count)
		TextView tv_score_count;

		private MyItemClickListener myItemClickListener;

		public ScoreDetailHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(ScoreDetail.DataBean data){
			if (!TextUtils.isEmpty(data.getTask_name())){
				tv_score_type.setText(data.getTask_name());
			}
			if (!TextUtils.isEmpty(data.getCreated_at())){
				tv_score_date.setText(data.getCreated_at());
			}
				tv_score_count.setText("+ "+ data.getScore());



		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


