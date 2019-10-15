package com.osiris.food.home;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.ContenDetail;
import com.osiris.food.model.LessonDetail;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnUrlClickListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class ContentDetailActivity extends BaseActivity {


	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.iv_content)
	ImageView ivContent;
	@BindView(R.id.tv_content)
	TextView tvContent;
	@BindView(R.id.scroll_view)
	NestedScrollView scroll_view;
	@BindView(R.id.iv_default)
	ImageView iv_default;
	@BindView(R.id.title)
	RelativeLayout title;

	private int id = 0;
	private boolean isLesson;
	private int lessonType = 0;
	private int visited = -1;
	private int titleHeight = 0;
	private int screanHeight = 0;
	private int scrollHeight = 0;
	private int score = 0;
	private boolean isMsg;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_content_detail;
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void init() {

		id = getIntent().getIntExtra("id", 0);
		isLesson = getIntent().getBooleanExtra("lesson", false);
		lessonType = getIntent().getIntExtra("lesson_type", 0);
		visited = getIntent().getIntExtra("visited", -1);
		isMsg = getIntent().getBooleanExtra("msg",false);
		RichText.initCacheDir(this);
		//tvContent.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		scroll_view.setOnScrollChangeListener(new View.OnScrollChangeListener() {

			@Override
			public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				if (scroll_view.getScrollY() == 0) {
					//顶部
				}

				View contentView = scroll_view.getChildAt(0);
				if (contentView != null && contentView.getMeasuredHeight() == (scroll_view.getScrollY() + scroll_view.getHeight())) {
					//底部
					if (visited == 0) {
						uploadRead();
						if (lessonType > 0) {
							uploadTask(lessonType);
						}
					}
				}
			}
		});
		LogUtils.d("zkf iv_default isVisible  " + iv_default.getVisibility());


		title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				// TODO Auto-generated method stub
				title.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				titleHeight = title.getMeasuredHeight();
				LogUtils.d("zkf title height:" + title.getMeasuredHeight());
			}
		});
		WindowManager windowManager =
				(WindowManager) getApplication().getSystemService(Context.
						WINDOW_SERVICE);
		final Display display = windowManager.getDefaultDisplay();
		Point outPoint = new Point();
		if (Build.VERSION.SDK_INT >= 19) {
			// 可能有虚拟按键的情况
			display.getRealSize(outPoint);
		} else {
			// 不可能有虚拟按键
			display.getSize(outPoint);
		}
		int mRealSizeWidth;//手机屏幕真实宽度
		int mRealSizeHeight;//手机屏幕真实高度
		mRealSizeHeight = outPoint.y;
		mRealSizeWidth = outPoint.x;
		screanHeight = mRealSizeHeight;
		LogUtils.d("zkf mRealSizeHeight:" + mRealSizeHeight);

		/*scroll_view.setOnScrollChangeListener(new  View.OnScrollChangeListener()) {
			@Override
			public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				if (scroll_view.getScrollY() == 0) {
					//顶部
				}

				View contentView = svView.getChildAt(0);
				if (contentView != null && contentView.getMeasuredHeight() == (scroll_view.getScrollY() + scroll_view.getHeight())) {
					//底部
				}

			}
		});*/
		//	uploadTask();
		getData();

	}

	@OnClick({R.id.rl_back})
	void onClik(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}


	private void getData() {

		String url;
		if (isLesson) {
			url = ApiRequestTag.API_HOST + "/api/v1/lessons/" + id;
		} else if (isMsg){
			url = ApiRequestTag.API_HOST + "/api/v1/messages/" + id;
		} else{
			url = ApiRequestTag.API_HOST + "/api/v1/contents/" + id;
		}
		LogUtils.d("zkf url :" + url);

		showLoadDialog();
		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200 && json.get("status").getAsString().equals("success")) {


					if (isLesson) {
						LessonDetail.DataBean dataBean = JsonUtils.fromJson(json.get("data").getAsJsonObject(), LessonDetail.DataBean.class);
					//	tvContent.setText(Html.fromHtml(dataBean.getSummary()));
						RichText.from(dataBean.getSummary())
								.urlClick(new OnUrlClickListener() {
									@Override
									public boolean urlClicked(String url) {
										if (url.startsWith("code://")) {
											//	Toast.makeText(ContentDetailActivity.this, url.replaceFirst("code://", ""), Toast.LENGTH_SHORT).show();
											return true;
										}
										return false;
									}
								})
								.into(tvContent);
						tvTitle.setText(dataBean.getName());
						if (isMsg){
							tvTitle.setText("通知");
						}
						score = dataBean.getScore();

					} else {
						ContenDetail.DataBean dataBean = JsonUtils.fromJson(json.get("data").getAsJsonObject(), ContenDetail.DataBean.class);
					//	tvContent.setText(Html.fromHtml(dataBean.getContent()));
						RichText.from(dataBean.getContent())
								.urlClick(new OnUrlClickListener() {
									@Override
									public boolean urlClicked(String url) {
										if (url.startsWith("code://")) {
										//	Toast.makeText(ContentDetailActivity.this, url.replaceFirst("code://", ""), Toast.LENGTH_SHORT).show();
											return true;
										}
										return false;
									}
								})
								.into(tvContent);
						LogUtils.d("zkf dataBean.getThumb():" + dataBean.getThumb());
					/*if (!TextUtils.isEmpty(dataBean.getThumb())) {
						Picasso.with(mActivity)
								.load(dataBean.getThumb())
								.into(ivContent);
					}*/
						tvTitle.setText(dataBean.getTitle());
					}

					cancelLoadDialog();

				} else if (json.get("code").getAsInt() == 200 && json.get("status").getAsString().equals("error")) {

					tvTitle.setText("文章不存在");
					cancelLoadDialog();

				}
				scroll_view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// TODO Auto-generated method stub
						scroll_view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						scrollHeight = scroll_view.getMeasuredHeight();
						mHandler.sendEmptyMessage(1);
						LogUtils.d("zkf scroll_view height:" + scroll_view.getMeasuredHeight());
					}
				});


			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				cancelLoadDialog();
			}
		});

	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 1:
					LogUtils.d("zkf receive handler scrollHeight:" + scrollHeight +" titleHeight:" + titleHeight+" screanHeight:" + screanHeight);
					if ((scrollHeight + titleHeight) < screanHeight) {
						LogUtils.d("zkf receive handler < ");
						if (visited == 0) {
							LogUtils.d("zkf uploadRead");
							uploadRead();
							if (lessonType > 0) {
								uploadTask(lessonType);
							}
						}
					}

					break;
			}
		}
	};

	private void uploadTask(int type) {

		String url = ApiRequestTag.API_HOST + "/api/v1/report/task";
		Map<String, String> paramMap = new HashMap<>();
		LogUtils.d("zkf id:" + id);
		paramMap.put("task_id", String.valueOf(type));
		paramMap.put("object_id",String.valueOf(id));
		//paramMap.put("score",String.valueOf(score));
		LogUtils.d("zkf url:" + url + "  task_id: " + type+ "object_id: " + id);

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf upload task successResult:" + successResult);

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf upload task code:" + code);

			}
		});


	}

	private void uploadRead() {

		String url = ApiRequestTag.API_HOST + "/api/v1/report/lesson";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("lesson_id", String.valueOf(id));
		paramMap.put("score",String.valueOf(score));

		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf upload task successResult:" + successResult);

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf upload task code:" + code);

			}
		});


	}

}
