package com.fit.i_kit.Javaclasses;

public class DefinitionData {
    public String ques;
    public String ans;


    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }


    public DefinitionData(String ques, String ans) {
        this.ques = ques;
        this.ans = ans;
    }


}
