/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

import java.sql.Date;


public class StudentData {
    private String studentid;
    private String studentname;
    private String phonenumber;
    private String email;
    private String coursename;
    private String courseid;
    private Double grade;
    private Integer credit;
    private String year;
    private Date birth;
    private String gender;
    private String major;

    public StudentData(String studentid, String studentname, String phonenumber, String email, String year, Date birth, String gender, String major) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.year = year;
        this.birth = birth;
        this.gender = gender;
        this.major = major;
    }

    public StudentData(String studentid, String courseid, Double grade, Integer credit, String year) {
        this.studentid = studentid;
        this.courseid = courseid;
        this.grade = grade;
        this.credit = credit;
        this.year = year;
    }
    
      public StudentData(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getCourseid() {
        return courseid;
    }

    public Double getGrade() {
        return grade;
    }

    public Integer getCredit() {
        return credit;
    }

    public String getYear() {
        return year;
    }

    public Date getBirth() {
        return birth;
    }

    public String getGender() {
        return gender;
    }

    public String getMajor() {
        return major;
    }
}

