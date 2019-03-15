package com.shanutec.cn.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.util.L;
import com.shanutec.cn.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 抖音
 * Created by xinyu on 2018/1/6.
 */

public class TikTokController2 extends BaseVideoController {

    private ImageView thumb;


    //人物头像
    private CircleImageView mAvatar;
    //人物点击量
    private TextView mLike;
    private TextView mMsg;
    private TextView mShare;
    private Love mLove;
    private ImageView mPause;

    public TikTokController2(@NonNull Context context) {
        super(context);
    }

    public TikTokController2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TikTokController2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_tiktok_controller2;
    }

    @Override
    protected void initView() {
        super.initView();
        thumb = mControllerView.findViewById(R.id.iv_thumb);
        mAvatar = mControllerView.findViewById(R.id.mAvatar);
        mLike = mControllerView.findViewById(R.id.mLike);
        mMsg = mControllerView.findViewById(R.id.mMsg);
        mShare = mControllerView.findViewById(R.id.mShare);
        mLove = mControllerView.findViewById(R.id.mLove);
        mPause = mControllerView.findViewById(R.id.mPause);
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);
        switch (playState) {
            case IjkVideoView.STATE_IDLE:
                thumb.setVisibility(VISIBLE);
              //  mPause.setVisibility(INVISIBLE);
                Log.i("GGG", "STATE_IDLE ");
                break;
            case IjkVideoView.STATE_PLAYING:
                thumb.setVisibility(GONE);
             //   mPause.setVisibility(INVISIBLE);
                Log.i("GGG", "STATE_PLAYING " + " " + "播放状态下开始播放");
                break;
            case IjkVideoView.STATE_PREPARED:
              //  mPause.setVisibility(INVISIBLE);
                Log.i("GGG", "STATE_PREPARED " + "视频缓冲完毕，准备开始播放时回调");
                break;
            case IjkVideoView.STATE_PREPARING:
              //  mPause.setVisibility(INVISIBLE);
                Log.i("GGG", "STATE_PREPARING " + " 开始准备播放（直接播放）");
                break;
            case IjkVideoView.STATE_PAUSED:
           //     mPause.setVisibility(VISIBLE);

                Log.i("GGG", "STATE_PAUSED ");
                break;
            case IjkVideoView.STATE_BUFFERING:
                Log.i("GGG", "STATE_BUFFERING ");
                break;
            case IjkVideoView.STATE_BUFFERED:
                Log.i("GGG", "STATE_BUFFERED ");
                break;
            case IjkVideoView.STATE_PLAYBACK_COMPLETED:

                Log.i("GGG", "STATE_PLAYBACK_COMPLETED ");
                break;
            case IjkVideoView.STATE_ERROR:

                Log.i("GGG", "STATE_ERROR ");
                break;


        }
    }

    public CircleImageView getmAvatar() {
        return mAvatar;
    }

    public Love getmLove() {
        return mLove;
    }

    public ImageView getmPause() {
        return mPause;
    }
    public TextView getmMsg() {
        return mMsg;
    }

    public TextView getmShare() {
        return mShare;
    }

    public TextView getmLike() {
        return mLike;
    }

    public ImageView getThumb() {
        return thumb;
    }
}
