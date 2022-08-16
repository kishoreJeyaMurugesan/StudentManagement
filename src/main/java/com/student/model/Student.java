package model;


import java.sql.Timestamp;


public class Student {
    private long id;
    private String name;
    private String email;
    private Timestamp date;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String toString() {
        return "\tStudentId : "+ id  + "\t StudentName : " + name+ "\t StudentEmail : " + email+"\tStudentDate : "+ date;
    }
}
