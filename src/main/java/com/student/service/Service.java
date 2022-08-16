package com.student.service;


import model.Department;
import model.Student;
import model.Subject;

import java.util.List;


public interface Service {
    boolean insertDetails(final Student student, final Department department, final Subject subject);
    List getAllDetails(final Student student, final Department department,final Subject subject);
    List getDetails(final Student student,final Department department, final Subject subject);
    boolean removeDetails(final Student student,final Department department);
    boolean updateDetails(final Student student, final Department department, final Subject subject,final int option);
}