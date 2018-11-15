package com.fit.i_kit.Javaclasses;

import java.io.Serializable;

public class ProgramData implements Serializable{

    public String queno;
    public String ques;
    public String ans;



    public String op;
    //public String output;


    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public ProgramData(String queno, String ques, String ans ,String op) {
        this.queno = queno;
        this.ques = ques;
        this.ans = ans;
        this.op=op;
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