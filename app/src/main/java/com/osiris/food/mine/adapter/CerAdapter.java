package com.osiris.food.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.model.Certification;
import com.osiris.food.view.widget.MyItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


	private MyItemClickListener myItemClickListener;

	private List<Certification.DataBean> dataList = new ArrayList<>();


	public CerAdapter(List<Certification.DataBean> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<Certification.DataBean> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_certification, parent,false);//解决宽度不能铺满

		return new CerHolder(view,myItemClickListener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((CerHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class CerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_cer_name)
		TextView tv_cer_name;
		@BindView(R.id.iv_cer)
		ImageView iv_cer;



		private MyItemClickListener myItemClickListener;

		public CerHolder(View itemView,MyItemClickListener myItemClickListener) {
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

		public void bindData(Certification.DataBean data){

			if (!TextUtils.isEmpty(data.getThumb())){
				Picasso.with(itemView.getContext())
						.load(data.getThumb())
						.into(iv_cer);
			}else {
				iv_cer.setBackgroundResource(R.drawable.ic_place);
			}

			tv_cer_name.setText(data.getName());
		}
	}


	public void setOnItemClick(MyItemClickListener myItemClickListener){
		this.myItemClickListener = myItemClickListener;
	}
}


