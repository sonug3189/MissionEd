package com.missionedappdev.missoned;

public class Student {

    private String name,emai,mobile,grade,hackos,paid,id;

    public Student(){

    }

    public Student(String name, String emai, String mobile, String grade, String hackos, String paid,String id) {
        this.name = name;
        this.emai = emai;
        this.mobile = mobile;
        this.grade = grade;
        this.hackos = hackos;
        this.paid = paid;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getHackos() {
        return hackos;
    }

    public void setHackos(String hackos) {
        this.hackos = hackos;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
