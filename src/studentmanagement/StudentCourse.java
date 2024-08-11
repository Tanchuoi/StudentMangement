/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagement;

/**
 *
 * @author Laptop DELL
 */
public class StudentCourse {
    private String studentid;
    private String courseid;
    private Double grade;

    public StudentCourse(String studentid, String courseid, Double grade) {
        this.studentid = studentid;
        this.courseid = courseid;
        this.grade = grade;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getCourseid() {
        return courseid;
    }

    public Double getGrade() {
        return grade;
    }
    
}
