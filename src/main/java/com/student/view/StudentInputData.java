package com.student.view;


import com.student.exception.NegativeNumberException;
import com.student.exception.NameMismatchException;
import com.student.exception.EmailMismatchException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StudentInputData {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Validation VALIDATION = new Validation();

    public  long getStudentId() {
        long id = 0;

        while (id == 0) {
            id = checkStudentId();
        }
        return id;
    }

    public long checkStudentId() {
        int validId = 0;

        System.out.print("Enter Id :");
        final String id = SCANNER.nextLine();

        final Pattern pattern = Pattern.compile("[0-9]+");
        final Matcher match = pattern.matcher(id);

        if (!match.find()) {
            System.out.println("Please Enter Validate Id ");
            return validId;
        }

        try {
            validId = VALIDATION.validateId(id);
        } catch (NegativeNumberException exception) {
            return validId;
        }
        return validId;
    }

    public double getStudentMark() {
        double mark = 0;

        while (mark == 0) {
            mark = checkStudentMark();
        }
        return mark;
    }

    public double checkStudentMark() {
        double validMark = 0;

        System.out.print("Enter Student Mark :");
        final String mark = SCANNER.nextLine();

        final Pattern pattern = Pattern.compile("[0-9]+");
        final Matcher match = pattern.matcher(mark);

        if (!match.find()) {
            System.out.println("Please Enter Validate Mark");
            return validMark;
        }
        try {
            validMark = VALIDATION.validateMark(mark);
        } catch (NegativeNumberException exception) {
            return validMark;
        }
        return validMark;
    }

    public String getStudentName() {
        String name = null;

        while (name == null) {
            name = checkStudentName();
        }
        return name;
    }

    public String checkStudentName() {
        System.out.print("Enter Name : ");
        String name = SCANNER.nextLine();
        String validName = null;

        try {
            validName = VALIDATION.validateName(name);
        } catch (NameMismatchException e) {
            return validName;
        }
        return name;
    }

    public String getStudentEmail() {
        String email = null;

        while (email == null) {
            email = checkStudentEmail();
        }
        return email;
    }

    public String checkStudentEmail() {
        System.out.print("Enter Student Email : ");
        final String email = SCANNER.nextLine();
        String validEmail = null;

        try {
            validEmail = VALIDATION.validateEmail(email);
        } catch (EmailMismatchException e) {
            return validEmail;
        }
        return email;
    }

    public Timestamp getStudentDate() {
        final Date date = new Date();
        final Long time = date.getTime();
        final Timestamp timestamp = new Timestamp(time);
        return timestamp;
    }
}