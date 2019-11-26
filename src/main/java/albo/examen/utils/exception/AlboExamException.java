package albo.examen.utils.exception;

import java.io.Serializable;

public class AlboExamException extends RuntimeException implements Serializable {

    private int code = 500;

    public AlboExamException() {
        super();
    }

    public AlboExamException(String message) {
        super(message);
    }

    public AlboExamException(String message, Exception e) {
        super(message, e);
    }

    public AlboExamException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
