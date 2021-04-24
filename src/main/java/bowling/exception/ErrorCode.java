package bowling.exception;

public enum ErrorCode {

    INVALID_NAME("N001", "Name should be 3 English letters"),

    INVALID_POINT("P001", "Point should be number between 0~10"),
    INVALID_POINT_SUM("P002", "Point in single frame cannot exceed 10"),

    INVALID_POINT_COUNT("PS001", "Point count must be positive");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}