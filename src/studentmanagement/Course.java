/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

/**
 *
 * @author Tan
 */
public class Course {
    private String courseid;
    private String coursename;
    private Integer credits;
    private Double grade;

    public Course(String courseid, String coursename, Integer credits) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.credits = credits;       
    }

    public String getCourseid() {
        return courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public Integer getCredits() {
        return credits;
    }

    public Double getGrade() {
        return grade;
    }
    
}
