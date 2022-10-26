package stackoverflow.global.exception.exceptionCode;

import lombok.Getter;

public enum ExceptionCode {
    INVALID_ACCOUNT_STATUS(400, "유효하지 않은 계정","계정상태를 확인해주세요."),
    ACCOUNT_NOT_FOUND(404, "계정을 찾을 수 없음","계정을 찾을 수 없습니다."),
    QUESTION_NOT_FOUND(404, "질문을 찾을 수 없음","질문을 찾을 수 없습니다."),
    ANSWER_NOT_FOUND(404, "답변을 찾을 수 없음","답변을 찾을 수 없습니다."),
    ACCOUNT_EXISTS(409, "중복된 계정","계정이 존재합니다."),
    NOT_IMPLEMENTATION(501, "지원하지 않는 서비스","서비스가 존재하지 않습니다.");

    @Getter
    private int status;
    @Getter
    private String exception;
    @Getter
    private String message;

    ExceptionCode(int status, String exception, String message) {
        this.status = status;
        this.exception = exception;
        this.message = message;
    }
}
