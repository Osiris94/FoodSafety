package com.osiris.food.train.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.model.VideoDetailBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class VideoDetailFragment extends BaseFragment {

    @BindView(R.id.tv_video_detail)
    TextView tv_video_detail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_video_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initVideoDetailView(VideoDetailBean videoDetailBean) {
        tv_video_detail.setText(Html.fromHtml(videoDetailBean.getData().getIntro()));
    }

}
