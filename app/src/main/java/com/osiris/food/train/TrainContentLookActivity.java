package com.osiris.food.train;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.event.DefaultEvent;
import com.osiris.food.event.UploadVideoInfo;
import com.osiris.food.model.VideoDetailBean;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.train.fragment.CommentFragment;
import com.osiris.food.train.fragment.VideoDetailFragment;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.utils.T;
import com.osiris.food.view.PagerSlidingTabStrip;
import com.tencent.liteav.demo.play.SuperPlayerModel;
import com.tencent.liteav.demo.play.SuperPlayerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainContentLookActivity extends AppCompatActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rlBack;
	@BindView(R.id.tv_title)
	TextView tvTitle;
	@BindView(R.id.superVodPlayerView)
	SuperPlayerView mSuperPlayerView;
	@BindView(R.id.tab_strip)
	PagerSlidingTabStrip tab_strip;
	@BindView(R.id.viewPager)
	ViewPager mViewPager;
	private String[] title;
	private String videoPath;

	private int mId;
	private String mPic;
	private int score = 0;
	private int lessonId = 0;

	private Context mActivity;
	private SuperPlayerModel model = new SuperPlayerModel();
	private long currentTime = System.currentTimeMillis();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_train_content_look);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		EventBus.getDefault().register(this);
		mActivity = this;
		ButterKnife.bind(this);
		//去掉标题栏（ActionBar实际上是设置在标题栏上的）

		init();
	}

	private void init() {
		mId = getIntent().getIntExtra("v_id", 0);
		mPic = getIntent().getStringExtra("pic");
		Log.e("xzw", mId + "");
		getVideoDetail();

		title = new String[]{getResources().getString(R.string.detail), "评论"};

		mViewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager()));
		tab_strip.setViewPager(mViewPager);
		tab_strip.setTextSize((int) getResources().getDimension(R.dimen.sp16));
		//	videoplayer.getCurrentPositionWhenPlaying()


		rlBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int time = (int) ((System.currentTimeMillis() - currentTime)/1000);
				postEvent(new UploadVideoInfo(mId,0,time, score,lessonId));
				finish();
			}
		});
	}


	@Override
	protected void onResume() {
		super.onResume();
		model.videoURL = videoPath;
		model.title = " ";
		mSuperPlayerView.startShowPlay();
	}

	private void getVideoDetail() {
		String url = ApiRequestTag.API_HOST + "/api/v1/videos/" + mId;
		//String url = ApiRequestTag.API_HOST + "/api/v1/videos/" + 2;
		Log.e("xzw", url);
		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				Log.e("xzw", successResult);
				VideoDetailBean videoDetailBean = JsonUtils.deserialize(successResult, VideoDetailBean.class);
				if (successResult.contains("id")) {
					tvTitle.setText(videoDetailBean.getData().getName());
					videoPath =  videoDetailBean.getData().getPath();
					model.videoURL = videoPath;
					model.title = " ";
					mSuperPlayerView.startShowPlay();
					mSuperPlayerView.playWithMode(model);
//					videoplayer.setUp(videoDetailBean.getData().getPath()
//							, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoDetailBean.getData().getName());
//					if (!TextUtils.isEmpty(mPic)) {
//						Picasso.with(mActivity)
//								.load(mPic)
//								.into(videoplayer.thumbImageView);
//					}
					score = videoDetailBean.getData().getScore();
					lessonId = videoDetailBean.getData().getLesson_id();
					EventBus.getDefault().post(videoDetailBean);
				} else {
					T.showShort(mActivity, "信息加载错误，请稍后重试");
					finish();
				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				Log.e("xzw", msg);
			}
		});
	}



	private class myPagerAdapter extends FragmentPagerAdapter {

		VideoDetailFragment fragment1;
		CommentFragment fragment2;

		public myPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					fragment1 = new VideoDetailFragment();
					return fragment1;

				case 1:
					fragment2 = new CommentFragment();
					return fragment2;

				default:
					return null;
			}
		}

		@Override
		public int getCount() {

			return title.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return title[position];
		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		int time = (int) ((System.currentTimeMillis() - currentTime)/1000);
		postEvent(new UploadVideoInfo(mId,0,time, score,lessonId));
		finish();
	}

	@Override
	protected void onPause() {
		super.onPause();


	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
		mSuperPlayerView.release();
		mSuperPlayerView.resetPlayer();

	}


	protected void postEvent(Object obj) {
		EventBus.getDefault().post(obj);
	}

	@Subscribe
	public void defaultEventHandler(DefaultEvent event) {
		// not handle
	}

}
