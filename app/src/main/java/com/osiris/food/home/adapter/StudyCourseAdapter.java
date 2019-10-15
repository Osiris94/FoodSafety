package com.osiris.food.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.osiris.food.R;
import com.osiris.food.model.StudyCourse;
import com.osiris.food.view.widget.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudyCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private MyItemClickListener myItemClickListener;

	private List<StudyCourse> dataList = new ArrayList<>();

	private Context context;

	public StudyCourseAdapter(List<StudyCourse> dataList, Context context) {
		this.dataList = dataList;
		this.context = context;
	}

	public void setDataList(List<StudyCourse> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study, parent, false);//解决宽度不能铺满

		return new StudyCourseHolder(view, myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((StudyCourseHolder) holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}


	class StudyCourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


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

		public StudyCourseHolder(View itemView, MyItemClickListener myItemClickListener) {
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

		public void bindData(StudyCourse data) {
			tv_name.setText(data.getCourseName());
			tv_course.setText(String.format(itemView.getResources().getString(R.string.txt_course_name), data.getCategory()));
			tv_time.setText(String.format(itemView.getResources().getString(R.string.txt_course_time), data.getCourseTime()));
			if (data.getCourseLook().equals("00:00:00")){
				tv_look_time.setText("未观看");
				tv_look_time.setTextColor(itemView.getResources().getColor(R.color.txt_gray_151));
			}else {
				tv_look_time.setText("已观看");
				tv_look_time.setTextColor(itemView.getResources().getColor(R.color.color_blue_btn));
			}

			tv_date.setText(data.getStartTime() + "至" + data.getEndTime());
			try {
				if (!TextUtils.isEmpty(data.getThumb())) {
					RequestOptions options = new RequestOptions()
							.placeholder(R.color.color_blue_btn)
							.error(R.color.color_blue_btn)
							.centerCrop();
					Glide.with(context).load(data.getThumb()).apply(options).into(iv_cover);
				}
			} catch (Exception e) {
				Log.e("xzw", e.getMessage());
			}

		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener) {
		this.myItemClickListener = myItemClickListener;
	}
}


