package com.osiris.food.train;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.osiris.food.R;
import com.osiris.food.base.BaseActivity;
import com.osiris.food.network.ApiRequestTag;
import com.osiris.food.network.NetRequest;
import com.osiris.food.network.NetRequestResultListener;
import com.osiris.food.utils.T;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyTrainingActivity extends BaseActivity {


	@BindView(R.id.edt_name)
	EditText edt_name;
	@BindView(R.id.edt_sex)
	EditText edt_sex;
	@BindView(R.id.edt_phone)
	EditText edt_phone;
	@BindView(R.id.edt_document)
	EditText edt_document;
	@BindView(R.id.edt_document_num)
	EditText edt_document_num;
	@BindView(R.id.rl_back)
	RelativeLayout rl_back;
	@BindView(R.id.tv_title)
	TextView tv_title;
	@BindView(R.id.iv_pic)
	ImageView iv_pic;
	@BindView(R.id.edt_company_name)
	EditText edtCompanyName;

	private static final int REQUEST_CODE_GALLERY = 0x10;// 图库选取图片标识请求码
	private static final int CROP_PHOTO = 0x12;// 裁剪图片标识请求码
	private static final int STORAGE_PERMISSION = 0x20;// 动态申请存储权限标识


	private File imageFile = null;// 声明File对象
	private Uri imageUri = null;// 裁剪后的图片uri

	@Override
	public int getLayoutResId() {
		return R.layout.activity_training_apply;
	}

	@Override
	public void init() {
		tv_title.setText(getString(R.string.txt_title_train_apply));
		edt_document_num.setText("食品安全培训证书");

	}


	@OnClick({R.id.tv_cancel, R.id.tv_confirm, R.id.iv_pic, R.id.rl_back})
	void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_cancel:
				finish();
				break;
			case R.id.tv_confirm:
				submit();
				break;
			case R.id.iv_pic:
				gallery();
				break;
			case R.id.rl_back:
				finish();
				break;
		}
	}

	private void submit() {
		String name = edt_name.getText().toString().trim();
		String sex = edt_sex.getText().toString().trim();
		String phone = edt_phone.getText().toString().trim();
		String idCard = edt_document.getText().toString().trim();
		String documentNum = edt_document_num.getText().toString().trim();
		if (imageFile == null || !imageFile.exists()) {
			T.showShort(mActivity, "清先添加证书");
			return;
		}
		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(phone)
				|| TextUtils.isEmpty(idCard) || TextUtils.isEmpty(documentNum)) {
			T.showShort(mActivity, "请将信息输入完整！");
			return;
		}

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("cert_name", documentNum);
		paramMap.put("name", name);
		paramMap.put("gender", sex);
		paramMap.put("phone", phone);
		paramMap.put("id_no", idCard);
		String url = ApiRequestTag.API_HOST + "/api/v1/users/identification";
		NetRequest.postFile(url, ApiRequestTag.REQUEST_DATA, imageFile, paramMap, new NetRequestResultListener() {
			@Override
			public void requestSuccess(int tag, String successResult) {
				Log.e("xzw", successResult);
				if (successResult.contains("success")) {
					T.showShort(mActivity, "申请成功");
					finish();
				}
			}

			@Override
			public void requestFailure(int tag, int code, String msg) {
				T.showShort(mActivity, "提交失败");
				Log.e("xzw", "error " + msg);
			}
		});
	}

	/**
	 * 图库选择图片
	 */
	private void gallery() {

		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		// 以startActivityForResult的方式启动一个activity用来获取返回的结果
		startActivityForResult(intent, REQUEST_CODE_GALLERY);

	}

	/**
	 * 接收#startActivityForResult(Intent, int)调用的结果
	 *
	 * @param requestCode 请求码 识别这个结果来自谁
	 * @param resultCode  结果码
	 * @param data
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {// 操作成功了

			switch (requestCode) {

				case REQUEST_CODE_GALLERY:// 图库选择图片

					Uri uri = data.getData();// 获取图片的uri

					Intent intent_gallery_crop = new Intent("com.android.camera.action.CROP");
					intent_gallery_crop.setDataAndType(uri, "image/*");

					// 设置裁剪
					intent_gallery_crop.putExtra("crop", "true");
					intent_gallery_crop.putExtra("scale", true);
					// aspectX aspectY 是宽高的比例
					intent_gallery_crop.putExtra("aspectX", 1);
					intent_gallery_crop.putExtra("aspectY", 1);
					// outputX outputY 是裁剪图片宽高
					intent_gallery_crop.putExtra("outputX", 400);
					intent_gallery_crop.putExtra("outputY", 400);

					intent_gallery_crop.putExtra("return-data", false);

					// 创建文件保存裁剪的图片
					createImageFile();
					imageUri = Uri.fromFile(imageFile);

					if (imageUri != null) {
						intent_gallery_crop.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
						intent_gallery_crop.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
					}

					startActivityForResult(intent_gallery_crop, CROP_PHOTO);

					break;

				case CROP_PHOTO:// 裁剪图片

					try {

						if (imageUri != null) {
							displayImage(imageUri);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					break;

			}

		}
	}

	/**
	 * 创建File保存图片
	 */
	private void createImageFile() {

		try {

			if (imageFile != null && imageFile.exists()) {
				imageFile.delete();
			}
			// 新建文件
			imageFile = new File(Environment.getExternalStorageDirectory(),
					System.currentTimeMillis() + "galleryDemo.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 显示图片
	 *
	 * @param imageUri 图片的uri
	 */
	private void displayImage(Uri imageUri) {
		try {
			// glide根据图片的uri加载图片
			RequestOptions options = new RequestOptions()
					.placeholder(R.drawable.bg_auth_third)//图片加载出来前，显示的图片
					.fallback(R.drawable.bg_auth_third) //url为空的时候,显示的图片
					.error(R.drawable.bg_auth_third)//图片加载失败后，显示的图片
					.diskCacheStrategy(DiskCacheStrategy.NONE)
					.centerCrop();

			if (TextUtils.isEmpty(imageUri.toString())) {
				Glide.with(this)
						.load(R.drawable.bg_auth_third)
						.into(iv_pic);
			} else {
				Glide.with(this)
						.load(imageUri)
						.apply(options)
						.into(iv_pic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
