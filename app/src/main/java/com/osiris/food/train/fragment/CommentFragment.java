package com.osiris.food.train.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.osiris.food.R;
import com.osiris.food.base.BaseFragment;
import com.osiris.food.model.VideoDetailBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import me.jessyan.autosize.utils.LogUtils;

public class CommentFragment extends BaseFragment {


    @BindView(R.id.main_commentList_elv)
    ExpandableListView mCommentExLV;
    @BindView(R.id.linear_replay)
    LinearLayout linear_replay;
    private CommentExListViewAdapter mCommentExListViewAdapter;
    private ArrayList<CommentItem> mCommentItemList = new ArrayList<>();
    private AlertDialog mCommentEditDialog;


    @Override
    protected int setLayout() {
        return R.layout.fragment_commentl;
    }

    @Override
    protected void initView() {

        mCommentExListViewAdapter = new CommentExListViewAdapter(mContext, mCommentItemList);
        mCommentExLV.setAdapter(mCommentExListViewAdapter);
        mCommentExLV.setGroupIndicator(null);
        mCommentExLV.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                LogUtils.d("zkf add replay");


                v.setClickable(true);
                return true;
            }
        });
        mCommentExLV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                LogUtils.d("zkf add child");
                return false;
            }
        });


        expandAllGroup();


        createCommentEditDialog();
    }

    private void expandAllGroup() {
        // 默认展开每一个分组
        for (int i = 0; i < mCommentExListViewAdapter.getGroupCount(); i++) {
            mCommentExLV.expandGroup(i);
        }
    }

    @Override
    protected void initData() {

    }

    private void createCommentEditDialog() {
        View commentInputView = getLayoutInflater().inflate(R.layout.dialog_comment, null);
        final EditText commentEdit = (EditText) commentInputView.findViewById(R.id.dialogComment_commentContentInput_edt);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请输入内容");
        builder.setView(commentInputView);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String commentContent = commentEdit.getText().toString().trim();
                CommentItem commentItem = new CommentItem(R.drawable.bg_login_icon, "当前用户名", getCurrentDateTime(), commentContent, null);
                if (!commentContent.equals("")) {
                    mCommentItemList.add(commentItem);

                    mCommentExListViewAdapter.notifyDataSetChanged();
                    expandAllGroup();
                }
            }
        });

        mCommentEditDialog = builder.create();
    }

    private String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return year + "-" + convertNumToString(month) + "-" + convertNumToString(day) + " " + convertNumToString(hour) + ":" + convertNumToString(minute) + ":" + second;
    }

    private String convertNumToString(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return "" + num;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initVideoDetailView(VideoDetailBean videoDetailBean) {
        initTestCommentItemList(videoDetailBean);
    }

    private void initTestCommentItemList(VideoDetailBean videoDetailBean) {
        mCommentItemList.clear();
        //mCommentItemList = new ArrayList<CommentItem>();
        List<VideoDetailBean.DataBean.CommentsBean> commentsBeans = videoDetailBean.getData().getComments();
        if (commentsBeans != null && commentsBeans.size() > 0) {
            for (int i = 0; i < commentsBeans.size(); i++) {
                VideoDetailBean.DataBean.CommentsBean bean = commentsBeans.get(i);
                CommentItem tempCommentItem = new CommentItem(R.drawable.bg_login_icon,
                        bean.getOwner().getName(), bean.getCreated_at(), bean.getContent(), null);
                mCommentItemList.add(tempCommentItem);
            }
        }
        mCommentExListViewAdapter.notifyDataSetChanged();
    }
}
