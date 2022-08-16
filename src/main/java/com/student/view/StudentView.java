package com.student.view;


import com.student.controller.StudentController;
import model.Department;
import model.Student;
import model.Subject;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class StudentView {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static StudentController STUDENT_CONTROLLER = new StudentController();
    private final static StudentInputData STUDENT_INPUT_DATA = new StudentInputData();
    private final Student student = new Student();
    private final Department department = new Department();
    private final Subject subject = new Subject();
    private void studentCrud() {
        int choice;

        do {
            System.out.println("1.INSERT\n2.DISPLAY\n3.SEARCH\n4.DELETE\n5.UPDATE");
            System.out.print("Enter Your Choice : ");
            choice = SCANNER.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    getAllStudent();
                    break;
                case 3:
                    getOneStudent();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 5:
                    updateStudent();
                    break;
                default:
                    System.out.println("Please Enter Valid Number");
            }
        } while (choice != 0);
    }

    private void addStudent() {
        System.out.println("Enter StudentDetails");
        student.setName(STUDENT_INPUT_DATA.getStudentName());
        student.setEmail(STUDENT_INPUT_DATA.getStudentEmail());
        student.setDate(STUDENT_INPUT_DATA.getStudentDate());
        System.out.println("Enter StudentDepartmentDetails");
        department.setDepartment_name(STUDENT_INPUT_DATA.getStudentName());
        System.out.println("Enter StudentSubjects");
        subject.setSubject_name(STUDENT_INPUT_DATA.getStudentName());


        try {
            if (STUDENT_CONTROLLER.insertStudentDetails(student,department,subject)) {
                System.out.println("Successfully Inserted");
            } else {
                System.out.println("Insert Not Successfully");
            }
        } catch (Exception exception) {
            addStudent();
        }
    }

    private void getAllStudent() {
        final List list = STUDENT_CONTROLLER.getAllStudentDetails(student, department, subject);
        final Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void getOneStudent() {
        final long searchingId = STUDENT_INPUT_DATA.getStudentId();

        student.setId(searchingId);

        final List list = STUDENT_CONTROLLER.getStudentDetails(student, department, subject);
        final  Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void removeStudent() {
        final long deleteId = STUDENT_INPUT_DATA.getStudentId();

        student.setId(deleteId);

        if (!STUDENT_CONTROLLER.removeStudentDetails(student,department)) {
            System.out.println("Successfully Deleted");
        } else {
            System.out.println("Check Id Number");
        }
    }

    private void updateStudent() {
        student.setId(STUDENT_INPUT_DATA.getStudentId());
        int option ;

        do {
            System.out.println("Enter your UpdateOption");
            System.out.println("1.Name\n2.Email\n3.DepartmentName\n4.SubjectName\n5.Exit:0");
            option = SCANNER.nextInt();

            switch (option) {
                case 1:
                    student.setName(STUDENT_INPUT_DATA.getStudentName());
                break;
                case 2:
                    student.setEmail(STUDENT_INPUT_DATA.getStudentEmail());
                break;
                case 3:
                    department.setDepartment_name(STUDENT_INPUT_DATA.getStudentName());
                break;
                case 4:
                    subject.setSubject_name(STUDENT_INPUT_DATA.getStudentName());
                    break;
                case 5:
                    if(option == 0)
                     break;
                default:
                    System.out.println("Please enter Valid Number");
            }
            if (!STUDENT_CONTROLLER.updateStudentDetails(student, department, subject, option)) {
                System.out.println("Successfully updated");
            } else {
                System.out.println("Update Not Successfully");
            }

        } while (option != 0);
    }

    public static void main(String[] args) {
        final StudentView studentView = new StudentView();
        studentView.studentCrud();
    }
}
