package com.fit.i_kit.Javaclasses;

import java.io.Serializable;

public class QuizVideoData implements Serializable {
    private String vidid;
    private String optiona;
    private String optionb;
    private String optionc;
    private String answer;
    private int sno,vid;

    public QuizVideoData() {

    }


    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }


    public QuizVideoData(String vidid, String optiona, String optionb, String optionc, String answer, int sno,int vid) {
        this.vidid = vidid;
        this.optiona = optiona;
        this.optionb = optionb;
        this.optionc = optionc;
        this.answer = answer;
        this.sno = sno;
        this.vid=vid;
    }


    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }


    public String getVidid() {
        return vidid;
    }

    public void setVidid(String vidid) {
        this.vidid = vidid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
