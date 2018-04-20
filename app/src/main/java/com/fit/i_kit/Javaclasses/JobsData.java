package com.fit.i_kit.Javaclasses;

import android.os.Parcel;
import android.os.Parcelable;

public class JobsData {

    private int id;
    private String cname;
    private String crole;
    private String cqual;
    private String expi;
    private String walk;
    private String time;
    private String location;
    private String descr;
    private String rolesresp;
    private String lastdate;
    private String referlink;

    public JobsData(String cname, String crole, String cqual, String expi, String walk, String time, String location, String descr, String rolesresp, String lastdate, String referlink) {
        this.cname = cname;
        this.crole = crole;
        this.cqual = cqual;
        this.expi = expi;
        this.walk = walk;
        this.time = time;
        this.location = location;
        this.descr = descr;
        this.rolesresp = rolesresp;
        this.lastdate = lastdate;
        this.referlink = referlink;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
public JobsData(){}
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getRolesresp() {
        return rolesresp;
    }

    public void setRolesresp(String rolesresp) {
        this.rolesresp = rolesresp;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getReferlink() {
        return referlink;
    }

    public void setReferlink(String referlink) {
        this.referlink = referlink;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCrole() {
        return crole;
    }

    public void setCrole(String crole) {
        this.crole = crole;
    }

    public String getCqual() {
        return cqual;
    }

    public void setCqual(String cqual) {
        this.cqual = cqual;
    }

    public String getExpi() {
        return expi;
    }

    public void setExpi(String expi) {
        this.expi = expi;
    }

    public String getWalk() {
        return walk;
    }

    public void setWalk(String walk) {
        this.walk = walk;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
