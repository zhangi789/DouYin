package com.shanutec.cn;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;


import com.bumptech.glide.request.RequestOptions;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.shanutec.cn.adapter.TikTokAdapter;
import com.shanutec.cn.bean.VideoBean;
import com.shanutec.cn.util.BaseUtil;
import com.shanutec.cn.util.OnViewPagerListener;
import com.shanutec.cn.view.GlideApp;
import com.shanutec.cn.view.ScreenCallBack;
import com.shanutec.cn.view.TikTokController2;
import com.shanutec.cn.view.ViewPagerLayoutManager;

import java.util.List;

/**
 * 模仿抖音短视频
 * Created by xinyu on 2018/1/6.
 */

public class TikTokActivity extends AppCompatActivity {
    private static final String TAG = "TikTokActivity";
    private IjkVideoView mIjkVideoView;
    private TikTokController2 mTikTokController;
    private int mCurrentPosition;
    private RecyclerView mRecyclerView;
    private List<VideoBean> mVideoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.str_tiktok);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_tiktok);

        setStatusBarTransparent();

        mIjkVideoView = new IjkVideoView(this);
        PlayerConfig config = new PlayerConfig.Builder().setLooping().build();
        mIjkVideoView.setPlayerConfig(config);
        mTikTokController = new TikTokController2(this);
        mIjkVideoView.setVideoController(mTikTokController);
        mRecyclerView = findViewById(R.id.rv);

        mVideoList = BaseUtil.getTikTokVideoList();
        TikTokAdapter tikTokAdapter = new TikTokAdapter(mVideoList, this);
        ViewPagerLayoutManager layoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(tikTokAdapter);
        layoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                startPlay(0);


                Log.i("GGG","onInitComplete");
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (mCurrentPosition == position) {
                    mIjkVideoView.release();
                    Log.i("GGG","onPageRelease");
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (mCurrentPosition == position) return;
                startPlay(position);
                mCurrentPosition = position;

                Log.i("GGG","onPageSelected");
            }
        });
    }

    private void startPlay(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        FrameLayout frameLayout = itemView.findViewById(R.id.container);
        RequestOptions options = new RequestOptions().centerCrop();
        GlideApp.with(this).load(mVideoList.get(position).getThumb()).apply(options)
                .into(mTikTokController.getThumb());
        GlideApp.with(this).load(mVideoList.get(position).getThumb()).apply(options)
                .into(mTikTokController.getmAvatar());


        mTikTokController.getmAvatar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TikTokActivity.this,"请不要点我的头,,谢谢",Toast.LENGTH_LONG).show();
            }
        });
      /*  mTikTokController.getmLove().setClickCallBack(new ScreenCallBack() {
            @Override
            public void onScreenClick() {

                Log.i("GGG","onScreenClick");
                if (mIjkVideoView.isPlaying()){
                    mIjkVideoView.pause();
                }else{
                    mIjkVideoView.resume();
                }
            }
        });*/
        mTikTokController.getmLike().setText(mVideoList.get(position).getLike());
        mTikTokController.getmMsg().setText(mVideoList.get(position).getMsg());
        mTikTokController.getmShare().setText(mVideoList.get(position).getShare());
        ViewParent parent = mIjkVideoView.getParent();
        if (parent instanceof FrameLayout) {
            ((FrameLayout) parent).removeView(mIjkVideoView);
        }
        frameLayout.addView(mIjkVideoView);
        mIjkVideoView.setUrl(mVideoList.get(position).getUrl());
        mIjkVideoView.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
        mIjkVideoView.start();
    }

    /**
     * 把状态栏设成透明
     */
    private void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = TikTokActivity.this.getWindow().getDecorView();
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                WindowInsets defaultInsets = v.onApplyWindowInsets(insets);
                return defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.getSystemWindowInsetLeft(),
                        0,
                        defaultInsets.getSystemWindowInsetRight(),
                        defaultInsets.getSystemWindowInsetBottom());
            });
            ViewCompat.requestApplyInsets(decorView);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.transparent));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIjkVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIjkVideoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIjkVideoView.release();
    }
}
