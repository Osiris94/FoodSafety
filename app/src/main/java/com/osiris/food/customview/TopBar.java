package com.osiris.food.customview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osiris.food.R;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * by osiris
 */
public class TopBar extends RelativeLayout {
    private boolean leftImgIsShow;//左侧图标是否显示
    private Drawable leftImg;
    private TopbarLeftClickListener leftListener;

    private String leftText;//左边文字属性
    private float leftTextSize;
    private int leftTextColor;

    private String centerText;//中间文字属性
    private float centerTextSize;
    private int centerTextColor;

    private String rightText;//右边文字属性
    private float rightTextSize;
    private int rightTextColor;

    private boolean rightImgIsShow;//右侧图标是否显示
    private Drawable rightImg;

    //左右图标
    private ImageView img_left, img_right;
    //左中右文字
    private TextView txt_left, txt_center, txt_right;
    //图片文字布局
    private LayoutParams leftImgParams, leftTxtParams, centerParams, rightTxtParams, rightImgParams;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获得attrs.xml中自定的所有属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.topBar);
        //左图默认不显示
        leftImgIsShow = array.getBoolean(R.styleable.topBar_leftImgIsShow, false);
        if (leftImgIsShow) {
            leftImg = array.getDrawable(R.styleable.topBar_leftImg);
            img_left = new ImageView(context);
            img_left.setImageDrawable(leftImg);
            img_left.setId(generateViewId());//左图ImgageView设置ID，便于使用addRule时通过ID定位
            leftImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL);
            addView(img_left, leftImgParams);

            //左图点击事件
            img_left.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (leftListener != null)
                        leftListener.leftClick();
                }
            });
        }
        //根据属性ID左边文字属性值
        leftText = array.getString(R.styleable.topBar_leftText);
        leftTextSize = array.getDimension(R.styleable.topBar_leftTextSize, 15);
        leftTextColor = array.getColor(R.styleable.topBar_leftTextColor, Color.WHITE);
        //根据属性ID中间文字属性值
        centerText = array.getString(R.styleable.topBar_centerText);
        centerTextSize = array.getDimension(R.styleable.topBar_centerTextSize, 16);
        centerTextColor = array.getColor(R.styleable.topBar_centerTextColor, Color.WHITE);
        //根据属性ID右边文字属性值
        rightText = array.getString(R.styleable.topBar_rightText);
        rightTextSize = array.getDimension(R.styleable.topBar_rightTextSize, 15);
        rightTextColor = array.getColor(R.styleable.topBar_rightTextColor, Color.WHITE);
        //右图默认不显示
        rightImgIsShow = array.getBoolean(R.styleable.topBar_rightImgIsShow, false);
        if (rightImgIsShow) {
            rightImg = array.getDrawable(R.styleable.topBar_rightImg);
            img_right = new ImageView(context);
            img_right.setId(generateViewId());
            img_right.setImageDrawable(rightImg);
            rightImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rightImgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightImgParams.addRule(RelativeLayout.CENTER_VERTICAL);
            addView(img_right, rightImgParams);
            //右图点击事件
            img_right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightClick();
                }
            });
        }
        //属性获取完之后及时回收
        array.recycle();
        //左边文本赋值
        txt_left = new TextView(context);
        txt_left.setText(leftText);
        txt_left.setId(generateViewId());
        txt_left.setTextSize(leftTextSize);
        txt_left.setTextColor(leftTextColor);
        //左边文本设置位置
        leftTxtParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (null == leftImgParams) {
            leftTxtParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);    //addRule一个参数
        } else {
            leftTxtParams.addRule(RelativeLayout.RIGHT_OF, img_left.getId()); //addRule两个参数，第二个参数是相对控件的ID
            leftTxtParams.addRule(RelativeLayout.END_OF, img_left.getId());
        }
        leftTxtParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(txt_left, leftTxtParams);
        //中间文本赋值
        txt_center = new TextView(context);
        txt_center.setText(centerText);
        txt_center.setId(generateViewId());
        txt_center.setTextSize(centerTextSize);
        txt_center.setTextColor(centerTextColor);
        //中间文本定位
        centerParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        centerParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(txt_center, centerParams);
        //右边文本赋值
        txt_right = new TextView(context);
        txt_right.setText(rightText);
        txt_right.setId(generateViewId());
        txt_right.setTextSize(rightTextSize);
        txt_right.setTextColor(rightTextColor);
        //右边文本定位
        rightTxtParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (null == rightImgParams) {
            rightTxtParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);    //addRule一个参数
        } else {
            rightTxtParams.addRule(RelativeLayout.LEFT_OF, img_right.getId());
            rightTxtParams.addRule(RelativeLayout.START_OF, img_right.getId());
        }
        rightTxtParams.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(txt_right, rightTxtParams);

        //左边文字点击事件
        txt_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (leftListener != null)
                    leftListener.leftClick();
            }
        });
        //中间文字点击事件
        txt_center.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                centerClick();
            }
        });
        //右侧文字点击事件
        txt_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rightClick();
            }
        });
    }

    //结合下面的方法可以来或者随机并不重复的控件ID
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public interface TopbarLeftClickListener {
        public void leftClick();
    }

    public void setOnTopbarLeftClickListener(TopbarLeftClickListener listener) {
        this.leftListener = listener;
    }

    //中间点击，根据实际需求修改
    public void centerClick() {
        //Toast.makeText(getContext(), "中间被点击", Toast.LENGTH_SHORT).show();
    }

    //右侧点击，根据实际需求修改
    public void rightClick() {
        //Toast.makeText(getContext(), "右侧被点击", Toast.LENGTH_SHORT).show();
    }

    //对外暴露可以设置标题的方法
    public void setCenterText(String centerText) {
        if (null != txt_center) {
            txt_center.setText(centerText);
        }
    }
}