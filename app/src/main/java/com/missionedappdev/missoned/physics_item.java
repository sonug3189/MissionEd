package com.missionedappdev.missoned;

public class physics_item
{
    private String chapterName;
    private int imageRes;

    public physics_item(String chapterName, int imageRes) {
        this.chapterName = chapterName;
        this.imageRes = imageRes;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}