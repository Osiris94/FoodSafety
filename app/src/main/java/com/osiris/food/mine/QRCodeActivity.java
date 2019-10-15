package com.osiris.food.mine;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.utils.QRUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRCodeActivity extends BaseActivity {


	@BindView(R.id.iv_qr)
	ImageView ivQr;

	@Override
	public int getLayoutResId() {
		return R.layout.activity_arcode;
	}

	@Override
	public void init() {
		String qrStr = "jiangsutaizhoushishipiananquan";

		Bitmap qrCodeBitmap = QRUtils.createQRImage(qrStr, 400, 400);
		ivQr.setImageBitmap(qrCodeBitmap);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
