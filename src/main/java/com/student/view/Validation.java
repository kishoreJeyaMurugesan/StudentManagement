package com.student.view;


import com.student. exception.NegativeNumberException;
import com.student.exception.EmailMismatchException;
import com.student.exception.NameMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation {

    public double validateMark(final String mark) {
        final double userMark = Double.parseDouble(mark);

        if (userMark <= 0) {
            throw new NegativeNumberException("Exception Occurred Enter Valid UserMark ");
        }
        return userMark;
    }

    public int validateId(final String id) {
        final int userId = Integer.parseInt(id);

        if (userId <= 0) {
            throw new NegativeNumberException("Exception Occurred Enter Valid UserId ");
        }
        return userId;
    }

    public String validateName(final String name) {

        final Pattern pattern = Pattern.compile("[a-zA-Z]+");
        final Matcher match = pattern.matcher(name);

        if (!match.find()) {
            throw new NameMismatchException("Exception Occurred Enter Valid UserName");
        }
        return name;
    }

    public String validateEmail(final String email) {
        final String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher match = pattern.matcher(email);

        if (!match.find()) {
            throw new EmailMismatchException("Exception Occurred Enter valid Email");
        }
        return email;
    }
}