package com.missionedappdev.missoned;

import android.widget.ImageView;
import android.widget.TextView;

public class ItemChapterModel
{
    private ImageView mImageView;
    private TextView mTextView;

    public ItemChapterModel() { }

    public ItemChapterModel(ImageView mImageView,TextView mTextView)
    {
        this.mImageView=mImageView; this.mTextView=mTextView;
    }

    public void setImageView(ImageView mImageView) {
        this.mImageView = mImageView;
    }

    public void setTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }

}
