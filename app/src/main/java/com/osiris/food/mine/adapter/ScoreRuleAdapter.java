package com.osiris.food.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.ScoreDesc;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoreRuleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<ScoreDesc> dataList = new ArrayList<>();


	public ScoreRuleAdapter(List<ScoreDesc> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<ScoreDesc> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_rule, parent,false);//解决宽度不能铺满

		return new ScoreRuleHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((ScoreRuleHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class ScoreRuleHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_answer)
		TextView tv_answer;
		@BindView(R.id.tv_question)
		TextView tv_question;


		private MyItemClickListener myItemClickListener;

		public ScoreRuleHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(ScoreDesc data){

			tv_question.setText(data.getQuestion());
			tv_answer.setText(data.getAnswer());

		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


