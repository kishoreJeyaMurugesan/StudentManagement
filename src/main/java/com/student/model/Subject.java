package  model;

public class Subject {
    private long subject_id;
    private String subject_name;

    public long getSubject_id() {
        return subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_id(final long subject_id) {
        this.subject_id = subject_id;
    }

    public void setSubject_name(final String subject_name) {
        this.subject_name = subject_name;
    }

    public String toString() {
        return "\tSubjectName: " + subject_name;
    }
}
