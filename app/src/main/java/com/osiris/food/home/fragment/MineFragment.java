package com.osiris.food.home.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.mine.ConnectUsActivity;
import com.osiris.food.mine.MyCertificationActivity;
import com.osiris.food.mine.MyNotificationActivity;
import com.osiris.food.mine.MyOrderActivity;
import com.osiris.food.mine.MyStudyActivity;
import com.osiris.food.mine.PersonInfoActivity;
import com.osiris.food.mine.SettingActivity;
import com.osiris.food.mine.StudyScoreActivity;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.GlobalParams;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.LogUtils;

public class MineFragment extends BaseFragment {


    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_study_score)
    TextView tv_study_score;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_mine_userType)
    TextView tvMineUserType;
    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.iv_qr)
    ImageView iv_qr;
    @BindView(R.id.rl_mine_score)
    RelativeLayout rl_mine_score;

    @Override
    protected int setLayout() {
        return R.layout.fragmnent_mine;
    }

    @Override
    protected void initView() {
        tv_name.setText(GlobalParams.user_name);
        if (!TextUtils.isEmpty(GlobalParams.avatar)) {
            Picasso.with(getActivity())
                    .load(GlobalParams.avatar)
                    .into(iv_avatar);
        }

        rl_mine_score.setVisibility(View.GONE);

        switch (GlobalParams.type) {
            case 1:
                tvMineUserType.setText("监管人员");
                break;
            case 2:
                tvMineUserType.setText("食品安全协管员");
                break;
            case 3:
                tvMineUserType.setText("食品安全管理员");
                break;
            case 4:
                tvMineUserType.setText("食品经营业主");
                break;
            case 5:
                tvMineUserType.setText("食品从业人员");
                break;
        }

    }


    @Override
    protected void initData() {


    }

    @OnClick({R.id.rl_mine_order, R.id.rl_mine_score, R.id.rl_mine_study, R.id.rl_mine_certificate,
            R.id.rl_mine_notification, R.id.rl_mine_contract, R.id.rl_mine_setting, R.id.rl_personal_info})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_mine_order:
                Intent intent = new Intent(getActivity(), MyOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_mine_score:
                Intent intent2 = new Intent(getActivity(), StudyScoreActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_mine_study:
                Intent intent3 = new Intent(getActivity(), MyStudyActivity.class);
                startActivity(intent3);

                break;
            case R.id.rl_mine_certificate:
                Intent intent4 = new Intent(getActivity(), MyCertificationActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_mine_notification:
                Intent intent5 = new Intent(getActivity(), MyNotificationActivity.class);
                startActivity(intent5);
                break;
            case R.id.rl_mine_contract:
                Intent intent6 = new Intent(getActivity(), ConnectUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.rl_mine_setting:
                Intent intent7 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent7);

                break;
            case R.id.rl_personal_info:
                Intent intent8 = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(intent8);

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
                        String score = json.get("data").getAsJsonObject().get("user_point").getAsString();
                        tv_study_score.setText("学习积分 " + score);
                    }

                }
            }

            @Override
            public void requestFailure(int tag, int code, String msg) {
                LogUtils.d("zkf code111:" + code);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getTotalScore();

    }
}
