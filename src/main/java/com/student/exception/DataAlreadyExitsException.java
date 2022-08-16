package  com.student.exception;

public class DataAlreadyExitsException extends RuntimeException {

    public DataAlreadyExitsException(String message) {
        super(message);
    }
}
