package com.osiris.food.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.MyStudy;
import com.osiris.food.view.widget.MyItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<MyStudy.DataBean.VideoCourseBean> dataList = new ArrayList<>();


	public StudyAdapter(List<MyStudy.DataBean.VideoCourseBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<MyStudy.DataBean.VideoCourseBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study, parent,false);//解决宽度不能铺满

		return new StudyHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((StudyHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class StudyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_course)
		TextView tv_course;
		@BindView(R.id.tv_date)
		TextView tv_date;
		@BindView(R.id.tv_time)
		TextView tv_time;
		@BindView(R.id.tv_look_time)
		TextView tv_look_time;
		@BindView(R.id.tv_name)
		TextView tv_name;
		@BindView(R.id.iv_cover)
		ImageView iv_cover;

		private MyItemClickListener myItemClickListener;

		public StudyHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(MyStudy.DataBean.VideoCourseBean data){
			tv_name.setText(data.getName());
			tv_course.setText(String.format(itemView.getResources().getString(R.string.txt_course_name),data.getCategory_name()));
			tv_time.setText(String.format(itemView.getResources().getString(R.string.txt_course_time),data.getTime()));
			tv_look_time.setText(String.format(itemView.getResources().getString(R.string.txt_course_time_look),data.getVisited()));
			tv_date.setText(data.getStart_time()+"-"+data.getEnd_time());
			if (!TextUtils.isEmpty(data.getThumb())){
				Picasso.with(itemView.getContext())
						.load(data.getThumb())
						.into(iv_cover);
			}


		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


