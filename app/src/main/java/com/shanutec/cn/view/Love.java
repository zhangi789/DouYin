package com.shanutec.cn.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.shanutec.cn.R;
import com.shanutec.cn.util.BaseUtil;

import java.util.Random;

/**
 * @author 张海洋
 * @Date on 2019/03/06.
 * @org 上海..科技有限公司
 * @describe
 */
public class Love extends RelativeLayout {
    private Context mContext;

    float[] num = {-30, -20, 0, 20, 30};//随机心形图片角度
    long[] mHits = new long[3];
    private ScreenCallBack callBack;
    private boolean mScrolling;
    private float touchDownX;
    private float touchDownY;
    public Love(Context context) {
        super(context);
        initView(context);
    }

    public void setClickCallBack(ScreenCallBack callBack) {
        this.callBack = callBack;
    }

    public Love(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Love(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
            Log.i("GGG", "hah");
            final ImageView imageView = new ImageView(mContext);
            LayoutParams params = new LayoutParams(300, 300);
            params.leftMargin = (int) event.getX() - 150;
            params.topMargin = (int) event.getY() - 300;
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.icon_home_like_after));
            imageView.setLayoutParams(params);
            addView(imageView);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(scale(imageView, "scaleX", 2f, 0.9f, 100, 0))
                    .with(scale(imageView, "scaleY", 2f, 0.9f, 100, 0))
                    .with(rotation(imageView, 0, 0, num[new Random().nextInt(4)]))
                    .with(alpha(imageView, 0, 1, 100, 0))
                    .with(scale(imageView, "scaleX", 0.9f, 1, 50, 150))
                    .with(scale(imageView, "scaleY", 0.9f, 1, 50, 150))
                    .with(translationY(imageView, 0, -600, 800, 400))
                    .with(alpha(imageView, 1, 0, 300, 400))
                    .with(scale(imageView, "scaleX", 1, 3f, 700, 400))
                    .with(scale(imageView, "scaleY", 1, 3f, 700, 400));

            animatorSet.start();
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    removeViewInLayout(imageView);
                }
            });
        } else {
           // callBack.onScreenClick();
        }


        return super.onTouchEvent(event);
    }

    public static ObjectAnimator scale(View view, String propertyName, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , propertyName
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator translationX(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationX"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator translationY(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "translationY"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator alpha(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view
                , "alpha"
                , from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    public static ObjectAnimator rotation(View view, long time, long delayTime, float... values) {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", values);
        rotation.setDuration(time);
        rotation.setStartDelay(delayTime);
        rotation.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        return rotation;
    }


}
