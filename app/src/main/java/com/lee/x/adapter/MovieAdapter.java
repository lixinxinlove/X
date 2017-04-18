package com.lee.x.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lee.x.R;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by android on 2017/4/18.
 */
public class MovieAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MovieAdapter(List<String> data) {
        super(R.layout.item_movie, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ((JCVideoPlayerStandard) helper.getView(R.id.video_player)).setUp(item, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "视频");
        ((JCVideoPlayerStandard) helper.getView(R.id.video_player)).thumbImageView.setImageResource(R.mipmap.camera);
    }


}
