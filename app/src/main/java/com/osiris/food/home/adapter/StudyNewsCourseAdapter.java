package com.osiris.food.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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

public class StudyNewsCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


	private MyItemClickListener myItemClickListener;

	private List<StudyCourse> dataList = new ArrayList<>();

	private Context context;

	public StudyNewsCourseAdapter(List<StudyCourse> dataList, Context context) {
		this.dataList = dataList;
		this.context = context;
	}

	public void setDataList(List<StudyCourse> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_news, parent, false);//解决宽度不能铺满

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
//		@BindView(R.id.tv_time)
//		TextView tv_time;
//		@BindView(R.id.tv_look_time)
//		TextView tv_look_time;
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
			if (data.getVisited() == 1){
				String str = data.getCourseName()+"（已学习）";
				SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(str);
				int fstart=str.indexOf("（已学习）");
				int fend=fstart+"（已学习）".length();
				SpannableStringBuilder style=new SpannableStringBuilder(str);
				style.setSpan(new ForegroundColorSpan(itemView.getResources().getColor(R.color.color_blue_btn)),
						fstart,fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				tv_name.setText(style);


			}else {//			tv_name.setText(data.getCourseName()+"（已观看）");

				tv_name.setText(data.getCourseName());

			}
			tv_name.setSelected(true);

			//	tv_course.setText(String.format(itemView.getResources().getString(R.string.txt_course_name), data.getCategory()));
		//	tv_time.setText(String.format(itemView.getResources().getString(R.string.txt_course_time), data.getCourseTime()));
		//	tv_look_time.setText(String.format(itemView.getResources().getString(R.string.txt_course_time_look), data.getCourseLook()));
			tv_date.setText(data.getStartTime());
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


