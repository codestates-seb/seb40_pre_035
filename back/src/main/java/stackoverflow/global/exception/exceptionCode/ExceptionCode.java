package stackoverflow.global.exception.exceptionCode;

import lombok.Getter;

public enum ExceptionCode {

    NOT_FOUND(404, "요청하신 데이터를 찾을 수 없습니다.");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
