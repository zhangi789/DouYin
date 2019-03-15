package com.shanutec.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.shanutec.cn.R;
import com.shanutec.cn.bean.VideoBean;
import com.shanutec.cn.view.GlideApp;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TikTokAdapter extends RecyclerView.Adapter<TikTokAdapter.VideoHolder> {

    private List<VideoBean> videos;
    private Context context;

    public TikTokAdapter(List<VideoBean> videos, Context context) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_tik_tok, parent, false);
        return new VideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VideoHolder holder, int position) {
        VideoBean videoBean = videos.get(position);
        RequestOptions options = new RequestOptions().centerCrop();
        GlideApp.with(context).load(videoBean.getThumb()).apply(options)
                .into(holder.thumb);
        GlideApp.with(context).load(videoBean.getThumb()).apply(options)
                .into(holder.mAvatar);
        holder.mLike.setText(videoBean.getLike());
        holder.mMsg.setText(videoBean.getMsg());
        holder.mShare.setText(videoBean.getShare());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        private ImageView thumb;
        private CircleImageView mAvatar;
        private TextView mLike;
        private TextView mMsg;
        private TextView mShare;

        VideoHolder(View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.thumb);
            mAvatar = itemView.findViewById(R.id.mAvatar);
            mLike = itemView.findViewById(R.id.mLike);
            mMsg = itemView.findViewById(R.id.mMsg);
            mShare = itemView.findViewById(R.id.mShare);
        }
    }
}