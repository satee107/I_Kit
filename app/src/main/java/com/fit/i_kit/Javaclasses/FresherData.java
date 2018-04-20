package com.fit.i_kit.Javaclasses;

public class FresherData {

    public String ques;
    public String ans;

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }


    public FresherData(String ques, String ans) {
        this.ques = ques;
        this.ans = ans;
    }

}
