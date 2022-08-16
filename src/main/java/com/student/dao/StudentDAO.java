package com.student.dao;

import com.student.exception.DataAlreadyExitsException;
import com.student.exception.IdMismatchException;
import com.student.exception.DataUpdateAlreadyExitsException;
import com.student.exception.DetailsNotFoundException;
import model.Department;
import model.Student;
import model.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDAO {
    private final static DataBaseConnection DATABASE_CONNECTION = new DataBaseConnection();
    private final Connection connection = DATABASE_CONNECTION.getConnection();
    public boolean addStudent(final Student student) {
        final String insertStudentSql = "insert into student(name,email,AdmissionDate) values (?,?,?)";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(insertStudentSql);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setTimestamp(3, student.getDate());
            return preparedStatement.execute();
        } catch (SQLException exception) {
            throw new DataAlreadyExitsException("Exception Occurred");
        }
    }

    public boolean addDepartment(final Department department) {
        final String insertDepartmentSql = "insert into department(department_name) values (?)";

        try {
            final  PreparedStatement preparedStatementDepartment = connection.prepareStatement(insertDepartmentSql);

            preparedStatementDepartment.setString(1,department.getDepartment_name());

            return preparedStatementDepartment.execute();
        } catch (SQLException exception) {
           throw new DataAlreadyExitsException("Exception Occurred");
        }
    }

    public boolean addSubject(final Subject subject) {
        final String insertSubjectSql = "insert into subject(subject_name) values (?) ";

        try {
            final PreparedStatement preparedStatementSubject = connection.prepareStatement(insertSubjectSql);

            preparedStatementSubject.setString(1,subject.getSubject_name());

            return preparedStatementSubject.execute();
        } catch (SQLException exception) {
            throw new DataAlreadyExitsException("Exception Occurred");
        }
    }

    public boolean insertDetails(final Student student, final Department department, final Subject subject) {
        addStudent(student);
        addDepartment(department);
        addSubject(subject);
        return true;
    }

    public List getAllDetails(final Student student,final Department department,final Subject subject) {
        final List linkedList = new LinkedList();
        final List arrayList = new ArrayList();
        final String AllStudentSQL = "select student.id, student.name,student.email,student.AdmissionDate,department.department_name,subject.subject_name  from student inner join department on student.id = department.student_id inner join subject on department.id = subject.department_id;";

        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(AllStudentSQL);
            final ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString("name"));
                    student.setEmail(resultSet.getString("email"));
                    student.setDate(resultSet.getTimestamp("AdmissionDate"));
                    department.setDepartment_name(resultSet.getString("department_name"));
                    subject.setSubject_name(resultSet.getString("subject_name"));

                    arrayList.add(student.toString());
                    arrayList.add(department.toString());
                    arrayList.add(subject.toString());
                    linkedList.addAll(arrayList);
                }
        } catch (SQLException exception) {
            throw new DetailsNotFoundException("Exception Occurred");
        }
        return linkedList;
    }

    public List getDetails(final Student student, final Department department, final Subject subject) {
        final List arrayList = new ArrayList();
        final List  linkedList = new LinkedList();

        try {
            final Connection connection = DATABASE_CONNECTION.getConnection();
            final String selectStudentId = "select student.id, student.name,student.email,student.AdmissionDate,department.department_name,subject.subject_name from student inner join department on student.id = department.student_id inner join subject on department.id = subject.department_id where student.id = ?";
            final PreparedStatement preparedStatement = connection.prepareStatement(selectStudentId);

            preparedStatement.setLong(1,student.getId());
            final ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString("name"));
                    student.setEmail(resultSet.getString("email"));
                    student.setDate(resultSet.getTimestamp("AdmissionDate"));
                    department.setDepartment_name(resultSet.getString(("department_name")));
                    subject.setSubject_name(resultSet.getString("subject_name"));

                    arrayList.add(student.toString());
                    arrayList.add(department.toString());
                    arrayList.add(subject.toString());
                    linkedList.addAll(arrayList);
                }
        } catch (SQLException exception) {
            throw new DetailsNotFoundException("Exception Occurred");
        }
        return linkedList;
    }

    public boolean removeDetail(final Student student,final Department department) {
        final String searchDepartmentSQL = "select id from department where student_id = ?";
        final String deleteSubjectSQL = "delete from subject where department_id =?";
        final String deleteDepartmentSQL = "delete from department where student_id = ?";
        final String deleteStudentSQL = "delete from student where id = ?";

        try {
            try(final PreparedStatement preparedStatement = connection.prepareStatement(searchDepartmentSQL)) {
                preparedStatement.setLong(1, student.getId());

                final ResultSet resultSet =  preparedStatement.executeQuery();

                while (resultSet.next()) {
                    department.setDepartment_id(resultSet.getLong("id"));
                }

            }
            try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSubjectSQL)) {
                    preparedStatement.setLong(1, department.getDepartment_id());
                    preparedStatement.execute();
            }

            try(PreparedStatement preparedStatement = connection.prepareStatement( deleteDepartmentSQL)) {
                    preparedStatement.setLong(1, student.getId());
                    preparedStatement.execute();
            }

            try(PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentSQL)) {

                preparedStatement.setLong(1, student.getId());
                return preparedStatement.execute();
            }
        } catch (SQLException exception ) {
            throw new IdMismatchException("Exception Occurred");
        }
    }

    public boolean updateDetail(final Student student, final Department department, final Subject subject, final  int type) {
        final String updateNameSQL = "update student set name = ? where id = ?";
        final String updateEmailSQL = "update student set email = ? where id = ?";
        final String updateDepartmentNameSQL = "update department set department_name = ? where id = ? ";
        final String updateSubjectNameSQL = "update Subject set subject_name = ? where id = ? ";

        try {

            switch (type) {
                case 1:

                    try (PreparedStatement preparedStatementName = connection.prepareStatement(updateNameSQL)) {
                        preparedStatementName.setString(1, student.getName());
                        preparedStatementName.setLong(2, student.getId());
                        return preparedStatementName.execute();
                    }
                case 2:

                    try(PreparedStatement preparedStatementEmail = connection.prepareStatement(updateEmailSQL)) {

                        preparedStatementEmail.setString(1, student.getEmail());
                        preparedStatementEmail.setLong(2, student.getId());

                        return preparedStatementEmail.execute();
                    }
                case 3:

                    try (PreparedStatement preparedStatementDepartmentName = connection.prepareStatement(updateDepartmentNameSQL)) {

                        preparedStatementDepartmentName.setString(1, department.getDepartment_name());
                        preparedStatementDepartmentName.setLong(2, student.getId());

                        return preparedStatementDepartmentName.execute();
                    }
                case 4 :

                    try(PreparedStatement preparedStatementSubjectName = connection.prepareStatement(updateSubjectNameSQL)) {

                        preparedStatementSubjectName.setString(1, subject.getSubject_name());
                        preparedStatementSubjectName.setLong(2, student.getId());

                        return preparedStatementSubjectName.execute();
                    }
                }
        } catch (SQLException exception) {
            throw new DataUpdateAlreadyExitsException("Exception Occurred");
        }
        return false;
    }
}