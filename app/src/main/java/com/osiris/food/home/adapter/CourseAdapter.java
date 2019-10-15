package com.osiris.food.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.StudyCourse;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<StudyCourse> dataList = new ArrayList<>();


	public CourseAdapter(List<StudyCourse> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<StudyCourse> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent,false);//解决宽度不能铺满

		return new CourseHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((CourseHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


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

		public CourseHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(StudyCourse data){
			tv_course.setText(String.format(itemView.getResources().getString(R.string.txt_course_name),data.getCourseName()));
			tv_time.setText(String.format(itemView.getResources().getString(R.string.txt_course_time),data.getCourseTime()));
			tv_look_time.setText(String.format(itemView.getResources().getString(R.string.txt_course_time_look),data.getCourseLook()));
			tv_date.setText(data.getStartTime()+"-"+data.getEndTime());
		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


