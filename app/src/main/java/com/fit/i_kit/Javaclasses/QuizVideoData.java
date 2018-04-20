package com.fit.i_kit.Javaclasses;

public class QuizVideoData {
    private String vidid;
    private String optiona;
    private String optionb;
    private String optionc;
    private String answer;



    public QuizVideoData(String vidid, String optiona, String optionb, String optionc,String answer) {
        this.vidid = vidid;
        this.optiona = optiona;
        this.optionb = optionb;
        this.optionc = optionc;
        this.answer=answer;
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
