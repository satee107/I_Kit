package com.fit.i_kit.Javaclasses;

import com.google.android.youtube.player.YouTubePlayerView;

public class VideoData {
    private String vidid;
    private String vidtit;
    private String vidtime;
    private String vidname;

    public VideoData() {

    }


    public String getVidid() {
        return vidid;
    }

    public void setVidid(String vidid) {
        this.vidid = vidid;
    }

    public String getVidtit() {
        return vidtit;
    }

    public void setVidtit(String vidtit) {
        this.vidtit = vidtit;
    }

    public String getVidtime() {
        return vidtime;
    }

    public void setVidtime(String vidtime) {
        this.vidtime = vidtime;
    }

    public String getVidname() {
        return vidname;
    }

    public void setVidname(String vidname) {
        this.vidname = vidname;
    }


    public VideoData(String vidid,String vidtit, String vidtime, String vidname) {
        this.vidid = vidid;
        this.vidtit = vidtit;
        this.vidtime = vidtime;
        this.vidname = vidname;
    }
}
