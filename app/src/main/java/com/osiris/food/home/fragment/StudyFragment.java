package com.osiris.food.home.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.StudyFindEvent;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.train.ApplyTrainingActivity;
import com.osiris.food.train.FaceTraingActivity;
import com.osiris.food.train.TrainRecordActivity;
import com.osiris.food.train.TrainingContentActivity;
import com.osiris.food.utils.T;
import com.osiris.food.utils.TextUti;
import com.osiris.food.view.MyViewPager;
import com.osiris.food.view.PagerSlidingTabStrip;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

//公共课程
public class StudyFragment extends BaseFragment {

    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tab_strip;
    @BindView(R.id.viewPager)
    MyViewPager mViewPager;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.rl_apply_record)
    RelativeLayout rl_apply_record;
    @BindView(R.id.rl_training_record)
    RelativeLayout rl_training_record;
    @BindView(R.id.tv_study_score)
    TextView tv_study_score;
    @BindView(R.id.tv_total_score)
    TextView tv_total_score;
    @BindView(R.id.tv_complete_time)
    TextView tv_complete_time;
    @BindView(R.id.et_study_find)
    EditText etStudyFind;
    @BindView(R.id.btn_study_find)
    Button btnStudyFind;
    @BindView(R.id.tv_food_safe)
    TextView tvFoodSafe;

    private String[] title;


    @Override
    protected int setLayout() {
        return R.layout.fragmnent_study;
    }

    @Override
    protected void initView() {
        switch (GlobalParams.type) {
            case 1:
                tvFoodSafe.setText("监管人员 食品安全知识学习");
                break;
            case 2:
                tvFoodSafe.setText("食品安全协管员 食品安全知识学习");
                break;
            case 3:
                tvFoodSafe.setText("食品安全管理员 食品安全知识学习");
                break;
            case 4:
                tvFoodSafe.setText("食品经营业主 食品安全知识学习");
                break;
            case 5:
                tvFoodSafe.setText("食品从业人员 食品安全知识学习");
                break;
        }

        title = new String[]{getResources().getString(R.string.video_class),
                getResources().getString(R.string.major_class),
                getResources().getString(R.string.public_class)};
        mViewPager.setAdapter(new myPagerAdapter(getActivity().getSupportFragmentManager()));
        tab_strip.setViewPager(mViewPager);
        tab_strip.setTextSize((int) getResources().getDimension(R.dimen.sp16));

        etStudyFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    EventBus.getDefault().post(new StudyFindEvent(""));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getTotalScore();
    }

    @Override
    protected void initData() {

    }

    public void setFragment() {

    }


    @OnClick({R.id.rl_apply_record, R.id.rl_training_record,
            R.id.tv_face_check, R.id.tv_confirm_note, R.id.btn_study_find})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_apply_record:
                Intent intent = new Intent(getActivity(), TrainingContentActivity.class);
                startActivity(intent);

                break;
            case R.id.rl_training_record:
                Intent intent2 = new Intent(getActivity(), TrainRecordActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_face_check:
                Intent intent3 = new Intent(getActivity(), ApplyTrainingActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_confirm_note:
                Intent intent4 = new Intent(getActivity(), FaceTraingActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_study_find:
                String findMsg = etStudyFind.getText().toString().trim();
                EventBus.getDefault().post(new StudyFindEvent(findMsg));
                break;
        }
    }


    private void getTotalScore() {

        String url = ApiRequestTag.API_HOST + "/api/v1/users/points";

        NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
            @Override
            public void requestSuccess(int tag, String successResult) {
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(successResult).getAsJsonObject();
                if (json.get("code").getAsInt() == 200) {
                    if (null != json.get("data").getAsJsonObject().get("user_point").getAsString()) {
                        String user_point = json.get("data").getAsJsonObject().get("user_point").getAsString();
                        String surplus_point = json.get("data").getAsJsonObject().get("surplus_point").getAsString();
                        String total_point = json.get("data").getAsJsonObject().get("total_point").getAsString();
                        //textview 改动后命名有问题
                        tv_study_score.setText(total_point);
                        tv_total_score.setText(user_point);
                        tv_complete_time.setText(surplus_point);
                        GlobalParams.user_point = Integer.parseInt(user_point);
                    }

                }
            }

            @Override
            public void requestFailure(int tag, int code, String msg) {
                LogUtils.d("zkf code111:" + code);
            }
        });

    }


    private class myPagerAdapter extends FragmentPagerAdapter {


        PublicCourseFragment fragment1;
        PolicyRuleFragment fragment2;
        MajorCourseFragment fragment3;

        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment1 = new PublicCourseFragment();
                    return fragment1;
                case 1:
                    fragment2 = new PolicyRuleFragment();
                    return fragment2;
                case 2:
                    fragment3 = new MajorCourseFragment();
                    return fragment3;


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


}
