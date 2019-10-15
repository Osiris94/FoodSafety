package com.osiris.food.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.event.StudyFindEvent;
import com.osiris.food.home.adapter.StudyCourseAdapter;
import com.osiris.food.model.LearnsPulicBean;
import com.osiris.food.model.StudyCourse;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.train.TrainContentLookActivity;
import com.osiris.food.utils.JsonUtils;
import com.osiris.food.view.widget.MyItemClickListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.jessyan.autosize.utils.LogUtils;

public class PublicCourseFragment extends BaseFragment {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;

    private List<StudyCourse> dataList = new ArrayList<>();
    private StudyCourseAdapter dataAdapter;
    private boolean show = true;


    @Override
    protected int setLayout() {
        return R.layout.fragment_public_course;
    }

    @Override
    protected void initView() {

//		主讲：食品安全
//		2018-01-01至2019-01-01
//		课程时长：3分21秒
//		已观看时长：0分0秒
        // dataList.add(new StudyCourse("食品安全", "2018-01-01", "2019-01-01", "3分21秒", "0分0秒"));
        //dataList.add(new StudyCourse("食品安全", "2018-01-01", "2019-01-01", "3分21秒", "0分0秒"));
        //dataList.add(new StudyCourse("食品安全", "2018-01-01", "2019-01-01", "3分21秒", "0分0秒"));
        dataAdapter = new StudyCourseAdapter(dataList, getActivity());
        rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_data.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
        dataAdapter.setOnItemClick(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TrainContentLookActivity.class);
                intent.putExtra("v_id", dataList.get(position).getId());
                intent.putExtra("pic", dataList.get(position).getThumb());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    private String mfindMsg;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetStickyEvent(StudyFindEvent studyFind) {
        mfindMsg = studyFind.getStudyFindMsg();
        Log.e("xzww", "PublicCourseFragment " + mfindMsg);
        getClassList();
    }

    @Override
    public void onResume() {
        super.onResume();
        getClassList();
    }

    private void getClassList() {
        dataList.clear();
        String url = ApiRequestTag.API_HOST + "/api/v1/lessons/learning";
        LogUtils.d("zkf  url:" + url);
        Map<String, String> paramMap = new HashMap<>();
        //  paramMap.put("type", "0");
        NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {

            @Override
            public void requestSuccess(int tag, String successResult) {
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(successResult).getAsJsonObject().get("data").getAsJsonObject();
                if ((successResult.contains("video"))) {
                    LearnsPulicBean.DataBean.VideoBean[] videos = JsonUtils.fromJson(json.get("video").getAsJsonArray()
                            , LearnsPulicBean.DataBean.VideoBean[].class);
                    List<LearnsPulicBean.DataBean.VideoBean> videoBeansList = new ArrayList<>();
                    videoBeansList.addAll(Arrays.asList(videos));
                    for (int i = 0; i < videoBeansList.size(); i++) {
                        LearnsPulicBean.DataBean.VideoBean bean = videoBeansList.get(i);
                        StudyCourse studyCourse = new StudyCourse();
                        studyCourse.setId(bean.getId());
                        studyCourse.setCourseName(bean.getName());
                        studyCourse.setStartTime(bean.getStart_time());
                        studyCourse.setEndTime(bean.getEnd_time());
                        studyCourse.setCourseTime(bean.getTime());
                        studyCourse.setCourseLook(bean.getVisited());
                        studyCourse.setCategory(bean.getCategory());
                        studyCourse.setThumb(bean.getThumb());
                        if (TextUtils.isEmpty(mfindMsg)) {
                            dataList.add(studyCourse);
                        } else if (bean.getName().contains(mfindMsg)) {
                            dataList.add(studyCourse);
                        }
                    }
                }
                dataAdapter.notifyDataSetChanged();
                Log.e("xzw   xxx", successResult);
            }

            @Override
            public void requestFailure(int tag, int code, String msg) {
                Log.e("xzw", msg);
            }
        });
    }
}
