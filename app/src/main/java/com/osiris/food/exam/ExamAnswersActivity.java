package com.osiris.food.exam;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.model.ExamDetail;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import me.jessyan.autosize.utils.LogUtils;

public class ExamAnswersActivity extends BaseActivity {

	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.tv_current)
	TextView tvCurrent;
	@BindView(R.id.tv_total)
	TextView tvTotal;
	@BindView(R.id.tv_subject)
	TextView tvSubject;
	@BindView(R.id.checkbox_a)
	CheckBox checkboxA;
	@BindView(R.id.tv_answer_a)
	TextView tvAnswerA;
	@BindView(R.id.linear_a)
	LinearLayout linearA;
	@BindView(R.id.checkbox_b)
	CheckBox checkboxB;
	@BindView(R.id.tv_answer_b)
	TextView tvAnswerB;
	@BindView(R.id.linear_b)
	LinearLayout linearB;
	@BindView(R.id.checkbox_c)
	CheckBox checkboxC;
	@BindView(R.id.tv_answer_c)
	TextView tvAnswerC;
	@BindView(R.id.linear_c)
	LinearLayout linearC;
	@BindView(R.id.checkbox_d)
	CheckBox checkboxD;
	@BindView(R.id.tv_answer_d)
	TextView tvAnswerD;
	@BindView(R.id.linear_d)
	LinearLayout linearD;
	@BindView(R.id.tv_exam_title)
	TextView tv_exam_title;
	@BindView(R.id.tv_right_answer)
	TextView tv_right_answer;
	@BindView(R.id.tv_answer_result)
	TextView tv_answer_result;
	@BindView(R.id.tv_next_question)
	TextView tv_next_question;
	@BindView(R.id.tv_last_question)
	TextView tv_last_question;
	@BindView(R.id.tv_answer_score)
	TextView tv_answer_score;

	private int mExamId = 0;
	private List<ExamDetail.DataBean.QuestionItemsBean> dataList = new ArrayList<>();
	private int position = 0;

	//private boolean answered;
	private Realm mRealm;
	private int examLocalScore = 0;
	private int uplaodId = 0;
	private int is_simulate =0;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_exam_answers;
	}

	@Override
	public void init() {

		tv_title.setText(getString(R.string.exam_answer));
		mExamId = getIntent().getIntExtra("exam_id", 0);
		is_simulate = getIntent().getIntExtra("is_simulate",0);
		mRealm = Realm.getDefaultInstance();

		/*RealmResults<ExamLocal> userRealmResults = mRealm.where(ExamLocal.class).equalTo("examId",mExamId).findAll();

		for (ExamLocal u : userRealmResults) {
			if (u.getExamId() == mExamId) {
				tv_answer_result.setVisibility(View.VISIBLE);
				tv_right_answer.setVisibility(View.VISIBLE);
				checkboxA.setClickable(false);
				checkboxB.setClickable(false);
				checkboxC.setClickable(false);
				checkboxD.setClickable(false);
				answered = true;
				examLocalScore = u.getScore();
			}
		}*/


		getData();

		checkboxA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (dataList.get(position).getAnswer().length() == 1) {
					checkboxB.setChecked(false);
					checkboxC.setChecked(false);
					checkboxD.setChecked(false);
				}

				if (isChecked) {
					checkboxA.setChecked(true);
					dataList.get(position).setSelectAnswer("A");
					if (position == (dataList.size() - 1)) {
						tv_next_question.setText("提交");
					}
				} else {
					checkboxA.setChecked(false);
				}

			}
		});
		checkboxB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (dataList.get(position).getAnswer().length() == 1) {
					checkboxA.setChecked(false);
					checkboxC.setChecked(false);
					checkboxD.setChecked(false);
				}


				if (isChecked) {
					checkboxB.setChecked(true);
					dataList.get(position).setSelectAnswer("B");
					if (position == (dataList.size() - 1)) {
						tv_next_question.setText("提交");
					}
				} else {
					checkboxB.setChecked(false);
				}

			}
		});
		checkboxC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (dataList.get(position).getAnswer().length() == 1) {
					checkboxB.setChecked(false);
					checkboxA.setChecked(false);
					checkboxD.setChecked(false);
				}

				if (isChecked) {
					checkboxC.setChecked(true);
					dataList.get(position).setSelectAnswer("C");
					if (position == (dataList.size() - 1)) {
						tv_next_question.setText("提交");
					}
				} else {
					checkboxC.setChecked(false);
				}

			}
		});
		checkboxD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (dataList.get(position).getAnswer().length() == 1) {
					checkboxB.setChecked(false);
					checkboxC.setChecked(false);
					checkboxA.setChecked(false);
				}
				if (isChecked) {
					checkboxD.setChecked(true);
					dataList.get(position).setSelectAnswer("D");
					if (position == (dataList.size() - 1)) {
						tv_next_question.setText("提交");
					}
				} else {
					checkboxD.setChecked(false);
				}

			}
		});


	}

	@OnClick({R.id.rl_back, R.id.tv_last_question, R.id.tv_next_question, R.id.rl_submit})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.rl_back:
				finish();
				break;
			case R.id.tv_last_question:
				position--;
				if (position < 0) {
					position++;
					Toast.makeText(this, "当前是第一题", Toast.LENGTH_SHORT).show();
					break;
				} else {
					tv_next_question.setText("下一题");
					tvCurrent.setText(String.valueOf(position + 1));
					if (dataList.get(position).getType().equals("1") || dataList.get(position).getType().equals("4")) {
						tvSubject.setText(dataList.get(position).getQuestion() + "(单选题)");
					} else {
						tvSubject.setText(dataList.get(position).getQuestion() + "(多选题)");
					}
					checkboxA.setChecked(false);
					checkboxB.setChecked(false);
					checkboxC.setChecked(false);
					checkboxD.setChecked(false);
					linearA.setVisibility(View.VISIBLE);
					linearB.setVisibility(View.VISIBLE);
					linearC.setVisibility(View.VISIBLE);
					linearD.setVisibility(View.VISIBLE);
					LogUtils.d("zkf  ddsdsd:" + dataList.get(position).getSelectAnswer());
					if (!TextUtils.isEmpty(dataList.get(position).getSelectAnswer())) {
						if (dataList.get(position).getSelectAnswer().contains("A")) {
							checkboxA.setChecked(true);
						}
						if (dataList.get(position).getSelectAnswer().contains("B")) {
							checkboxB.setChecked(true);
						}
						if (dataList.get(position).getSelectAnswer().contains("C")) {
							checkboxC.setChecked(true);
						}
						if (dataList.get(position).getSelectAnswer().contains("D")) {
							checkboxD.setChecked(true);
						}
					}


				/*	if (answered) {


						RealmResults<ExamLocal> userRealmResults = mRealm.where(ExamLocal.class).equalTo("examId",mExamId).findAll();
						for (ExamLocal u : userRealmResults) {
							if (u.getAnswerId() == dataList.get(position).getId()) {

								String answer = u.getSelectAnswer();
								tv_right_answer.setText("正确答案：" + u.getAnswer());
								LogUtils.d("zkf answer:!!!" + u.getAnswer());
								if (!TextUtils.isEmpty(answer) && null != answer) {
									if (answer.contains("A")) {
										checkboxA.setChecked(true);
									}
									if (answer.contains("B")) {
										checkboxB.setChecked(true);
									}
									if (answer.contains("C")) {
										checkboxC.setChecked(true);
									}
									if (answer.contains("D")) {
										checkboxD.setChecked(true);
									}
								}

							}


						}


						String answer = "";
						if (checkboxA.isChecked()) {
							if (TextUtils.isEmpty(answer)) {
								answer = "A";
							}
						}
						if (checkboxB.isChecked()) {
							if (TextUtils.isEmpty(answer) && !answer.equals("")) {
								answer = answer + "|B";
							} else {
								answer = "B";
							}

						}
						if (checkboxC.isChecked() && !answer.equals("")) {
							if (TextUtils.isEmpty(answer)) {
								answer = answer + "|C";
							} else {
								answer = "C";
							}
						}
						if (checkboxD.isChecked()) {
							if (TextUtils.isEmpty(answer) && !answer.equals("")) {
								answer = answer + "|D";
							} else {
								answer = "D";
							}
						}
						if (answer.equals(dataList.get(position).getAnswer())) {
							tv_answer_result.setText("回答正确");
						} else {
							tv_answer_result.setText("回答错误");
						}
					}*/

					switch (dataList.get(position).getSelect().size()) {
						case 0:
							break;
						case 1:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							linearB.setVisibility(View.GONE);
							linearC.setVisibility(View.GONE);
							linearD.setVisibility(View.GONE);
							break;
						case 2:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							linearC.setVisibility(View.GONE);
							linearD.setVisibility(View.GONE);
							break;
						case 3:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							tvAnswerC.setText(dataList.get(position).getSelect().get(2));
							linearD.setVisibility(View.GONE);
							break;
						case 4:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							tvAnswerC.setText(dataList.get(position).getSelect().get(2));
							tvAnswerD.setText(dataList.get(position).getSelect().get(3));
							break;
					}
				}


				break;
			case R.id.tv_next_question:

				position++;

				if (position < dataList.size()) {


					checkboxA.setChecked(false);
					checkboxB.setChecked(false);
					checkboxC.setChecked(false);
					checkboxD.setChecked(false);
					linearA.setVisibility(View.VISIBLE);
					linearB.setVisibility(View.VISIBLE);
					linearC.setVisibility(View.VISIBLE);
					linearD.setVisibility(View.VISIBLE);
					tvCurrent.setText(String.valueOf(position + 1));
					if (dataList.get(position).getType().equals("1") || dataList.get(position).getType().equals("4")) {
						tvSubject.setText(dataList.get(position).getQuestion() + "(单选题)");
					} else {
						tvSubject.setText(dataList.get(position).getQuestion() + "(多选题)");
					}
					switch (dataList.get(position).getSelect().size()) {
						case 0:
							break;
						case 1:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							linearB.setVisibility(View.GONE);
							linearC.setVisibility(View.GONE);
							linearD.setVisibility(View.GONE);
							break;
						case 2:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							linearC.setVisibility(View.GONE);
							linearD.setVisibility(View.GONE);
							break;
						case 3:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							tvAnswerC.setText(dataList.get(position).getSelect().get(2));
							linearD.setVisibility(View.GONE);
							break;
						case 4:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							tvAnswerC.setText(dataList.get(position).getSelect().get(2));
							tvAnswerD.setText(dataList.get(position).getSelect().get(3));
							break;
					}
					if (!TextUtils.isEmpty(dataList.get(position).getSelectAnswer())) {
						if (dataList.get(position).getSelectAnswer().contains("A")) {
							checkboxA.setChecked(true);
						}
						if (dataList.get(position).getSelectAnswer().contains("B")) {
							checkboxB.setChecked(true);
						}
						if (dataList.get(position).getSelectAnswer().contains("C")) {
							checkboxC.setChecked(true);
						}
						if (dataList.get(position).getSelectAnswer().contains("D")) {
							checkboxD.setChecked(true);
						}

					}
					/*if (answered) {
						RealmResults<ExamLocal> userRealmResults = mRealm.where(ExamLocal.class).equalTo("examId",mExamId).findAll();
						for (ExamLocal u : userRealmResults) {
							if (u.getAnswerId() == dataList.get(position).getId()) {
								String answer = u.getSelectAnswer();
								tv_right_answer.setText("正确答案：" + u.getAnswer());
								LogUtils.d("zkf answer:" + u.getAnswer());
								if (!TextUtils.isEmpty(answer) && null != answer) {
									if (answer.contains("A")) {
										checkboxA.setChecked(true);
									}
									if (answer.contains("B")) {
										checkboxB.setChecked(true);
									}
									if (answer.contains("C")) {
										checkboxC.setChecked(true);
									}
									if (answer.contains("D")) {
										checkboxD.setChecked(true);
									}
								}


							}


						}
						String answer = "";
						if (checkboxA.isChecked()) {
							if (TextUtils.isEmpty(answer)) {
								answer = "A";
							}
						}
						if (checkboxB.isChecked()) {
							if (TextUtils.isEmpty(answer) && !answer.equals("")) {
								answer = answer + "|B";
							} else {
								answer = "B";
							}

						}
						if (checkboxC.isChecked() && !answer.equals("")) {
							if (TextUtils.isEmpty(answer)) {
								answer = answer + "|C";
							} else {
								answer = "C";
							}
						}
						if (checkboxD.isChecked()) {
							if (TextUtils.isEmpty(answer) && !answer.equals("")) {
								answer = answer + "|D";
							} else {
								answer = "D";
							}
						}
						if (answer.equals(dataList.get(position).getAnswer())) {
							tv_answer_result.setText("回答正确");
						} else {
							tv_answer_result.setText("回答错误");
						}

					}*/
				} else {
					position--;
					if (tv_next_question.getText().toString().equals("提交")) {
						LogUtils.d("zkf 提交");
					/*		mRealm.beginTransaction();

							for (ExamDetail.DataBean.QuestionItemsBean data : dataList) {

								ExamLocal examlocal = mRealm.createObject(ExamLocal.class);
								examlocal.setExamId(mExamId);
								examlocal.setAnswer(data.getAnswer());
								examlocal.setSelectAnswer(data.getSelectAnswer());
								examlocal.setAnswerId(data.getId());
							}
							mRealm.commitTransaction();*/

						//	RealmResults<ExamLocal> userRealmResults = mRealm.where(ExamLocal.class).equalTo("examId",mExamId).findAll();

						int rightCount = 0;
						int averageScore = 100 / (dataList.size());
//							if (userRealmResults.size() == dataList.size()) {
//								for (int i = 0; i < dataList.size(); i++) {
//									if (userRealmResults.get(i).getSelectAnswer().equals(dataList.get(i).getAnswer())) {
//										rightCount++;
//									}
//								}
//							}

						for (ExamDetail.DataBean.QuestionItemsBean questionItemsBean : dataList) {
							if (!TextUtils.isEmpty(questionItemsBean.getSelectAnswer())&&questionItemsBean.getSelectAnswer().equals(questionItemsBean.getAnswer())) {
								rightCount++;
							}
						}

						if (rightCount == dataList.size()) {
							examLocalScore = 100;
							saveScoreData();
						} else {
							LogUtils.d("zkf rightcount:" + rightCount + "  averageScore:" + averageScore);
							examLocalScore = rightCount * averageScore;
							saveScoreData();
						}


					//	finish();


					} else {
						Toast.makeText(this, "当前是最后一题", Toast.LENGTH_SHORT).show();
					}


					break;
				}


				break;
			case R.id.rl_submit:

				break;
		}
	}

	private void saveAttToDBWithPic(ExamDetail.DataBean.QuestionItemsBean data) {


		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mRealm.executeTransaction(new Realm.Transaction() {
					@Override
					public void execute(Realm realm) {

					}
				});
			}
		});
	}


	private void getData() {
		if (mExamId == 0)
			return;

		showLoadDialog();
		String url = ApiRequestTag.API_HOST + "/api/v1/papers/" + mExamId;
		LogUtils.d("zkf url :" + url);
		NetRequest.requestNoParamWithToken(url, ApiRequestTag.REQUEST_DATA, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {

				JsonParser parser = new JsonParser();
				JsonObject json = parser.parse(successResult).getAsJsonObject();
				if (json.get("code").getAsInt() == 200) {
					ExamDetail.DataBean dataBean = JsonUtils.fromJson(
							json.get("data").getAsJsonObject(), ExamDetail.DataBean.class);
					ExamDetail.DataBean.QuestionItemsBean[] data = JsonUtils.fromJson(
							json.get("data").getAsJsonObject().get("question_items").getAsJsonArray(), ExamDetail.DataBean.QuestionItemsBean[].class);
					if (dataList.size() > 0) {
						dataList.clear();
					}
					uplaodId =dataBean.getId();
					String title = JsonUtils.fromJson(json.get("data").getAsJsonObject().get("name").getAsString(), String.class);
					tv_exam_title.setText(title);

					dataList.addAll(Arrays.asList(data));
					tvTotal.setText("/" + String.valueOf(dataList.size()));
					if (dataList.get(position).getType().equals("1") || dataList.get(position).getType().equals("4")) {
						tvSubject.setText(dataList.get(position).getQuestion() + "(单选题)");
					} else {
						tvSubject.setText(dataList.get(position).getQuestion() + "(多选题)");
					}
					switch (dataList.get(position).getSelect().size()) {
						case 0:
							break;
						case 1:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							linearB.setVisibility(View.GONE);
							linearC.setVisibility(View.GONE);
							linearD.setVisibility(View.GONE);
							break;
						case 2:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							linearC.setVisibility(View.GONE);
							linearD.setVisibility(View.GONE);
							break;
						case 3:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							tvAnswerC.setText(dataList.get(position).getSelect().get(2));
							linearD.setVisibility(View.GONE);
							break;
						case 4:
							tvAnswerA.setText(dataList.get(position).getSelect().get(0));
							tvAnswerB.setText(dataList.get(position).getSelect().get(1));
							tvAnswerC.setText(dataList.get(position).getSelect().get(2));
							tvAnswerD.setText(dataList.get(position).getSelect().get(3));
							break;
					}


				/*	if (answered) {
						RealmResults<ExamLocal> userRealmResults = mRealm.where(ExamLocal.class).equalTo("examId",mExamId).findAll();

						int rightCount = 0;
						int averageScore = 100 / (userRealmResults.size());
						if (userRealmResults.size() == dataList.size()) {
							for (int i = 0; i < dataList.size(); i++) {
								if (!TextUtils.isEmpty(userRealmResults.get(i).getSelectAnswer())&&userRealmResults.get(i).getSelectAnswer().equals(dataList.get(i).getAnswer())) {
									rightCount++;
								}
							}
						}
						for (ExamLocal u : userRealmResults) {
							if (u.getAnswerId() == dataList.get(position).getId()) {

								String answer1 = u.getSelectAnswer();
								tv_right_answer.setText("正确答案：" + u.getAnswer());
								if (!TextUtils.isEmpty(answer1) && null != answer1) {
									if (answer1.contains("A")) {
										checkboxA.setChecked(true);
									}
									if (answer1.contains("B")) {
										checkboxB.setChecked(true);
									}
									if (answer1.contains("C")) {
										checkboxC.setChecked(true);
									}
									if (answer1.contains("D")) {
										checkboxD.setChecked(true);
									}
								}

								String answer = "";
								if (checkboxA.isChecked()) {
									if (TextUtils.isEmpty(answer)) {
										answer = "A";
									}
								}
								if (checkboxB.isChecked()) {
									if (TextUtils.isEmpty(answer) && !answer.equals("")) {
										answer = answer + "|B";
									} else {
										answer = "B";
									}

								}
								if (checkboxC.isChecked() && !answer.equals("")) {
									if (TextUtils.isEmpty(answer)) {
										answer = answer + "|C";
									} else {
										answer = "C";
									}
								}
								if (checkboxD.isChecked()) {
									if (TextUtils.isEmpty(answer) && !answer.equals("")) {
										answer = answer + "|D";
									} else {
										answer = "D";
									}
								}
								if (answer.equals(dataList.get(position).getAnswer())) {
									tv_answer_result.setText("回答正确");
								} else {
									tv_answer_result.setText("回答错误");
								}

							}


						}
						if (rightCount == dataList.size()) {
							tv_answer_score.setVisibility(View.VISIBLE);
							tv_answer_score.setText("100分");
							examLocalScore = 100;
							updateScoreData();
						} else {
							LogUtils.d("zkf rightcount:" + rightCount + "  averageScore:" + averageScore);
							tv_answer_score.setVisibility(View.VISIBLE);
							tv_answer_score.setText(rightCount * averageScore + "分");
							examLocalScore = rightCount * averageScore;
							updateScoreData();

						}

					}*/


				}
				cancelLoadDialog();

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf  code: " + code);
				cancelLoadDialog();
				Toast.makeText(ExamAnswersActivity.this,"积分不够，请再接再厉",Toast.LENGTH_SHORT).show();
				finish();
			}
		});


	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}

	private void updateScoreData() {
		if (mExamId == 0)
			return;

		showLoadDialog();
		String url = ApiRequestTag.API_HOST + "/api/v1/report/exam";

		Map<String, String> paramMap = new HashMap<>();
		//paramMap.put("user_id",String.valueOf(GlobalParams.user_id));
		//LogUtils.d("zkf userid:" + GlobalParams.user_id);
		paramMap.put("paper_id", String.valueOf(mExamId));
		LogUtils.d("zkf paper_id:" + mExamId);
		LogUtils.d("zkf score:" + examLocalScore);

		paramMap.put("score", String.valueOf(examLocalScore));

		LogUtils.d("zkf url :" + url);


		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf  successResult      sdd:" + successResult);
				cancelLoadDialog();

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf  code      code:" + code);
				cancelLoadDialog();

			}
		});

	}


	private void saveScoreData() {
		if (mExamId == 0)
			return;

		showLoadDialog();
		String url = ApiRequestTag.API_HOST + "/api/v1/report/exam";

		Map<String, String> paramMap = new HashMap<>();
		//paramMap.put("user_id",String.valueOf(GlobalParams.user_id));
		//LogUtils.d("zkf userid:" + GlobalParams.user_id);
		paramMap.put("paper_id", String.valueOf(uplaodId));
		paramMap.put("is_simulate",String.valueOf(is_simulate));
		LogUtils.d("zkf paper_id:" + mExamId);
		LogUtils.d("zkf score:" + examLocalScore);

		paramMap.put("score", String.valueOf(examLocalScore));

		LogUtils.d("zkf url :" + url);
		if (examLocalScore>60){
			Toast.makeText(this, "恭喜你通过考试，现在可以去领取证书了", Toast.LENGTH_SHORT).show();
		}else {
			Toast.makeText(this, "考试未通过，请再接再厉", Toast.LENGTH_SHORT).show();
		}



		NetRequest.requestParamWithToken(url, ApiRequestTag.REQUEST_DATA, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				LogUtils.d("zkf  successResult      sdd:" + successResult);
				cancelLoadDialog();
				if (successResult.contains("success")) {
					finish();
				}

			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				LogUtils.d("zkf  code      code:" + code);
				cancelLoadDialog();

			}
		});

	}


}
