package com.osiris.food.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.ScoreList;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private MyItemClickListener myItemClickListener;

	private List<ScoreList.DataBean> dataList = new ArrayList<>();


	public StudyScoreAdapter(List<ScoreList.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<ScoreList.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_score, parent, false);//解决宽度不能铺满

		return new StudyScoreHolder(view, myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((StudyScoreHolder) holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}


	class StudyScoreHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		@BindView(R.id.tv_study_title)
		TextView tvStudyTitle;
		@BindView(R.id.linear_title)
		LinearLayout linearTitle;
		@BindView(R.id.tv_btn_type)
		TextView tvBtnType;
		@BindView(R.id.tv_add_score)
		TextView tvAddScore;
		@BindView(R.id.tv_hint)
		TextView tv_hint;
		@BindView(R.id.tv_detail)
		TextView tv_detail;
		private MyItemClickListener myItemClickListener;

		public StudyScoreHolder(View itemView, MyItemClickListener myItemClickListener) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			this.myItemClickListener = myItemClickListener;
			tvBtnType.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (null != myItemClickListener) {
				myItemClickListener.onItemClick(itemView, getLayoutPosition());
			}
		}

		public void bindData(ScoreList.DataBean data) {

			if (!TextUtils.isEmpty(data.getName())) {
				tvStudyTitle.setText(data.getName());
			}

			tvAddScore.setText("+" + data.getScore());
			if (data.getBounce() == 0) {
				tv_hint.setVisibility(View.GONE);
			} else {
				tv_hint.setVisibility(View.VISIBLE);
			}


			if (data.getName().contains("每日首次登录")){
				tv_detail.setText("登录 "+"+"+data.getScore()+"/次 | 已获"+
						data.getMax_score()+"分/上线"+1+"分");
				if (data.getScore() == data.getMax_score()){
					tvBtnType.setText("已完成");
				}else {
					tvBtnType.setText("去登录");
				}
			}else if (data.getName().contains("阅读文章")){
				tv_detail.setText("阅读 "+"+"+data.getScore()+"/次 | 已获"+
						data.getMax_score()+"分/上线"+1+"分");
				if (data.getScore() == data.getMax_score()){
					tvBtnType.setText("已完成");
				}else {
					tvBtnType.setText("去看看");
				}
			} else if (data.getName().contains("观看视频")){
				tv_detail.setText("观看 "+"+"+data.getScore()+"/次 | 已获"+
						data.getMax_score()+"分/上线"+1+"分");
				if (data.getScore() == data.getMax_score()){
					tvBtnType.setText("已完成");
				}else {
					tvBtnType.setText("去看看");
				}
			}else if (data.getName().contains("文章学习市场")){
				tv_detail.setText("学习 "+"+"+data.getScore()+"/次 | 已获"+
						data.getMax_score()+"分/上线"+1+"分");
				if (data.getScore() == data.getMax_score()){
					tvBtnType.setText("已完成");
				}else {
					tvBtnType.setText("去学习");
				}
			}else if (data.getName().contains("视频学习市场")){
				tv_detail.setText("学习 "+"+"+data.getScore()+"/次 | 已获"+
						data.getMax_score()+"分/上线"+1+"分");
				if (data.getScore() == data.getMax_score()){
					tvBtnType.setText("已完成");
				}else {
					tvBtnType.setText("去学习");
				}
			}









		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener) {
		this.myItemClickListener = myItemClickListener;
	}
}


