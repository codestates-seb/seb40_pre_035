package stackoverflow.global.exception.exceptionCode;

import lombok.Getter;

public enum ExceptionCode {
    // 에러 메세지 선언부
    ACCOUNT_NOT_FOUND(404, "계정을 찾을 수 없습니다."),
    ACCOUNT_EXISTS(409, "계정이 존재합니다."),
    QUESTION_NOT_FOUND(404, "질문을 찾을 수 없습니다."),
    ANSWER_NOT_FOUND(404, "답변을 찾을 수 없습니다."),
    NOT_IMPLEMENTATION(501, "서비스가 존재하지 않습니다.");
//    INVALID_ACCOUNT_STATUS(400, "계정상태를 확인해주세요.");


    // 필요한 값이 있으면 추가하는 부분
    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
