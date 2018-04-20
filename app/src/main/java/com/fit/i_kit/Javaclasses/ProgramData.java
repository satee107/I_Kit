package com.fit.i_kit.Javaclasses;

public class ProgramData {

    public String queno;
    public String ques;
    public String ans;
    //public String output;




    public ProgramData(String queno, String ques, String ans) {
        this.queno = queno;
        this.ques = ques;
        this.ans = ans;
        //this.output = output;
    }


    public String getQueno() {
        return queno;
    }

    public void setQueno(String queno) {
        this.queno = queno;
    }

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

//    public String getOutput() {
//        return output;
//    }
//
//    public void setOutput(String output) {
//        this.output = output;
//    }
}