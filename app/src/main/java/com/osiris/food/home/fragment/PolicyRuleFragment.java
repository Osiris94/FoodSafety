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
import com.osiris.food.home.ContentDetailActivity;
import com.osiris.food.home.adapter.StudyNewsCourseAdapter;
import com.osiris.food.model.LearnsPulicBean;
import com.osiris.food.model.StudyCourse;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
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

//专业课程
public class PolicyRuleFragment extends BaseFragment {


	@BindView(R.id.rv_data)
	RecyclerView rv_data;

	private List<StudyCourse> dataList = new ArrayList<>();
	private StudyNewsCourseAdapter dataAdapter;


	@Override
	protected int setLayout() {
		return R.layout.fragment_public_course;
	}

	@Override
	protected void initView() {
		dataAdapter = new StudyNewsCourseAdapter(dataList, getActivity());
		rv_data.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
		rv_data.setAdapter(dataAdapter);
		dataAdapter.notifyDataSetChanged();
		dataAdapter.setOnItemClick(new MyItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(getActivity(), ContentDetailActivity.class);
				intent.putExtra("id", dataList.get(position).getId());
				intent.putExtra("lesson",true);
				intent.putExtra("lesson_type",8);
				intent.putExtra("visited",dataList.get(position).getVisited());
				startActivity(intent);
//				Intent intent = new Intent(getActivity(), TrainContentLookActivity.class);
//				intent.putExtra("v_id", dataList.get(position).getId());
//				intent.putExtra("pic", dataList.get(position).getThumb());
//				startActivity(intent);
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
		Log.e("xzww", "PolicyRuleFragment " + mfindMsg);
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
		//	paramMap.put("type", "1");
		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				Log.e("xzw", successResult);
				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject().get("data").getAsJsonObject();
				if ((successResult.contains("content"))) {
					LearnsPulicBean.DataBean.CoursesBean[] learnsMajorBean = JsonUtils.fromJson(json.get("content").getAsJsonArray()
							, LearnsPulicBean.DataBean.CoursesBean[].class);
					List<LearnsPulicBean.DataBean.CoursesBean> coursesBeansList = new ArrayList<>();
					coursesBeansList.addAll(Arrays.asList(learnsMajorBean));
					//	int learnId = learnsMajorBean.getData().getLesson_id();
					//	List<LearnsMajorBean.DataBean.ListBean> listBeans = learnsMajorBean.getData().getList();
					for (int i = 0; i < coursesBeansList.size(); i++) {
						LearnsPulicBean.DataBean.CoursesBean bean = coursesBeansList.get(i);
						StudyCourse studyCourse = new StudyCourse();
						//studyCourse.setLessonId(bean);
						studyCourse.setId(bean.getId());
						studyCourse.setCourseName(bean.getTitle());
						studyCourse.setStartTime(bean.getStart_time());
						studyCourse.setVisited(bean.getVisited());
						studyCourse.setVisited(bean.getVisited());
						studyCourse.setScore(bean.getScore());
						//	studyCourse.setEndTime(learnsMajorBean.getData().getLesson().getEnd_time());
						//	studyCourse.setCourseTime(bean.getTime());
						//	studyCourse.setCourseLook(bean.getVisited());
						//	studyCourse.setCategory(bean.getCategory());
						studyCourse.setThumb(bean.getThumb());
						if (TextUtils.isEmpty(mfindMsg)) {
							dataList.add(studyCourse);
						} else if (bean.getTitle().contains(mfindMsg)) {
							dataList.add(studyCourse);
						}
					}
					dataAdapter.notifyDataSetChanged();
				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				Log.e("xzw", msg);
			}
		});
	}


}
