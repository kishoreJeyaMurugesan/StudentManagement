package com.student.service;

import com.student.dao.StudentDAO;
import model.Department;
import model.Student;
import model.Subject;
import java.util.List;

public class StudentService implements Service {
    private final static StudentDAO STUDENT_DAO = new StudentDAO();

    public boolean insertDetails(final Student student, final Department department, final Subject subject) {
        return STUDENT_DAO.insertDetails(student,department,subject);
    }

    public List getAllDetails(final Student student,final Department department, final Subject subject) {
        return STUDENT_DAO.getAllDetails(student, department, subject);
    }

    public List getDetails(final Student student,final Department department, final Subject subject) {
        return STUDENT_DAO.getDetails(student, department, subject);
    }

    public boolean removeDetails(final Student student, final  Department department) {
        return STUDENT_DAO.removeDetail(student ,department);
    }

    public boolean updateDetails(final Student student, final Department department, final Subject subject, final  int option) {
         return  STUDENT_DAO.updateDetail(student ,department, subject, option);
    }
}