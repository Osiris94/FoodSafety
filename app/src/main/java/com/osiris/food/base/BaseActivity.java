package com.osiris.food.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.osiris.food.R;
import com.osiris.food.event.DefaultEvent;
import com.osiris.food.utils.MyActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

//        View decorView = getWindow().getDecorView();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            decorView.setSystemUiVisibility(flag);
//        }

        setContentView(getLayoutResId());
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        MyActivityUtils.addActivity(this);
        init();
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyActivityUtils.removeActivity(this);
        EventBus.getDefault().unregister(this);
    }

    public abstract int getLayoutResId();

    /**
     * 初始化逻辑
     */
    public abstract void init();

    /**
     * 返回上一页
     */
    public void backAction() {
        //finish();
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 跳转
     */
    public void startAction(@SuppressWarnings("rawtypes") Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("mydata", bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * 跳转
     */
    public void startActionWithFinish(@SuppressWarnings("rawtypes") Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转
     */
    public void startAction(@SuppressWarnings("rawtypes") Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * 跳转
     */
    public void startAction(@SuppressWarnings("rawtypes") Class cls, String key, int id) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(key, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void sendMessageToUI(Handler mHandler, int mWhat) {
        mHandler.sendEmptyMessage(mWhat);
    }

    public void sendMessageToUI(Handler mHandler, int mWhat, String mData) {
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putString("data", mData);
        msg.setData(bundle);
        msg.what = mWhat;
        mHandler.sendMessage(msg);
    }

    protected void postEvent(Object obj) {
        EventBus.getDefault().post(obj);
    }

    @Subscribe
    public void defaultEventHandler(DefaultEvent event) {
        // not handle
    }

    private volatile int loadDialogShowCount = 0;

    protected Dialog loadDialog;


    protected synchronized void showLoadDialog() {
        if (loadDialog == null) {
            loadDialog = new Dialog(this, R.style.loadingDialog);
            View progressContentView = LayoutInflater.from(this).inflate(R.layout
                    .layout_loading_dialog, null);
            ProgressBar pb = (ProgressBar) progressContentView.findViewById(R.id.pb);
            pb.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color
                    .base_color), PorterDuff.Mode.SRC_ATOP);
            loadDialog.setContentView(progressContentView);
            loadDialog.setCancelable(false);
            loadDialog.setCanceledOnTouchOutside(false);
        }
        if (!loadDialog.isShowing()) {
            loadDialog.show();
        }
        loadDialogShowCount++;
    }
    protected void cancelLoadDialog() {
        cancelLoadDialog(false);
    }

    protected synchronized void cancelLoadDialog(boolean force) {
        if (force) {
            loadDialogShowCount = 0;
        }
        loadDialogShowCount--;
        if (loadDialog != null && loadDialogShowCount <= 0) {
            loadDialog.cancel();
        }
    }
}
