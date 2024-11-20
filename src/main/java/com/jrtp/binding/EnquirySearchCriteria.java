package com.jrtp.binding;

public class EnquirySearchCriteria {
    public Integer userid;
    public String course;
    public String status;
    public String mode;
    public String studentName; // Corrected property name

    // Getters and Setters
    public String getStudentName() {
        return studentName; // Corrected getter
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName; // Corrected setter
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "EnquirySearchCriteria [userid=" + userid + ", course=" + course + ", status=" + status + ", mode=" + mode + ", studentName=" + studentName + "]";
    }
}
