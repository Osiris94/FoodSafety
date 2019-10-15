package com.osiris.food.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.osiris.food.R;
import com.osiris.food.event.DefaultEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
	protected Context mContext;
	protected View contentView;
	private Activity activity;


	/**
	 * 当fragment与activity发生关联时调用
	 *
	 * @param context 与之相关联的activity
	 */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		activity = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (contentView == null) {
			contentView = inflater.inflate(setLayout(), container, false);
			ButterKnife.bind(this, contentView);
			initView();
		}
		return contentView;
	}

	/**
	 * 绑定布局
	 *
	 * @return
	 */
	protected abstract int setLayout();

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	/**
	 * 初始化组件
	 */
	protected abstract void initView();

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}

	/**
	 * 设置数据等逻辑代码
	 */
	protected abstract void initData();

	/**
	 * 简化findViewById
	 *
	 * @param resId
	 * @param <T>
	 * @return
	 */
	protected <T extends View> T fvbi(int resId) {
		return (T) getView().findViewById(resId);
	}

	/**
	 * intent跳转
	 *
	 * @param context
	 * @param clazz
	 */
	protected void toClass(Context context, Class<? extends BaseActivity> clazz) {
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
	}

	/**
	 * intent带值跳转
	 *
	 * @param context
	 * @param clazz
	 * @param bundle
	 */
	protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle) {
		Intent intent = new Intent(context, clazz);
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

	/**
	 * 带返回值的跳转
	 *
	 * @param context
	 * @param clazz
	 * @param bundle
	 * @param requestCode
	 */
	protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle, int requestCode) {
		Intent intent = new Intent(context, clazz);
		intent.putExtras(bundle);
		getActivity().startActivityForResult(intent, requestCode);
	}

	protected void postEvent(Object obj) {
		EventBus.getDefault().post(obj);
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}


	@Subscribe
	public void defaultEventHandler(DefaultEvent event) {
		// not handle
	}

	private volatile int loadDialogShowCount = 0;

	protected Dialog loadDialog;

	protected synchronized void showLoadDialog() {
		if (loadDialog == null) {
			loadDialog = new Dialog(activity, R.style.loadingDialog);
			View progressContentView = LayoutInflater.from(activity).inflate(R.layout
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

	protected void  setActivity(Activity activity){
		this.activity = activity;
	}
}
