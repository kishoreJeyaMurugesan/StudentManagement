package com.student.controller;


import model.Department;
import model.Student;
import model.Subject;
import com.student.service.StudentService;
import java.util.List;


public class StudentController {
    private final static StudentService STUDENT_SERVICE = new StudentService();

    public boolean insertStudentDetails(final Student student,final Department department,final Subject subject) {
        return STUDENT_SERVICE.insertDetails(student, department, subject);
    }

    public List getAllStudentDetails(final Student student,final Department department,final Subject subject) {
        return STUDENT_SERVICE.getAllDetails(student, department, subject);
    }

    public List getStudentDetails(final Student student, final Department department, final Subject subject) {
        return  STUDENT_SERVICE.getDetails(student, department, subject);
    }

    public boolean removeStudentDetails(final Student student,final Department department) {
        return STUDENT_SERVICE.removeDetails(student, department);
    }

    public boolean updateStudentDetails(final Student student,final Department department, final Subject subject, final int option) {
        return STUDENT_SERVICE.updateDetails(student, department, subject, option);
    }
}
