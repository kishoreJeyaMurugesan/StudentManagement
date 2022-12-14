package model;

public class Department {

    private long department_id;
    private String department_name;

    public long getDepartment_id() {
        return department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_id(final long department_id) {
        this.department_id = department_id;
    }

    public void setDepartment_name(final String department_name) {
        this.department_name = department_name;
    }
    public String toString() {
        return "\tDepartment: " + department_name;
    }
}

