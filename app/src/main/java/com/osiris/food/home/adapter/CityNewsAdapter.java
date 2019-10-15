package com.osiris.food.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.PolicyList;
import com.osiris.food.view.widget.MyItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<PolicyList.DataBeanX.DataBean> dataList = new ArrayList<>();


	public CityNewsAdapter(List<PolicyList.DataBeanX.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<PolicyList.DataBeanX.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_news, parent,false);//解决宽度不能铺满

		return new PolicyHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((PolicyHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class PolicyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_news_name)
		TextView tv_news_name;
		@BindView(R.id.tv_news_type)
		TextView tv_news_type;
		@BindView(R.id.tv_news_date)
		TextView tv_news_date;
		@BindView(R.id.iv_news_pic)
		ImageView iv_news_pic;



		private MyItemClickListener myItemClickListener;

		public PolicyHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(PolicyList.DataBeanX.DataBean data){
			if (!TextUtils.isEmpty(data.getTitle())){
				tv_news_name.setText(data.getTitle());
			}
			if (!TextUtils.isEmpty(data.getCategory_name())){
				tv_news_type.setText(data.getCategory_name());
			}
			if (!TextUtils.isEmpty(data.getCreated_at())){
				tv_news_date.setText(data.getCreated_at().substring(0,10));
			}

			if (!TextUtils.isEmpty(data.getThumb())){
				Picasso.with(itemView.getContext())
						.load(data.getThumb())
						.into(iv_news_pic);
			}else {
				iv_news_pic.setBackgroundResource(R.drawable.ic_place);
			}

		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


