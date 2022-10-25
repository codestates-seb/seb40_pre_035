package stackoverflow.global.exception.advice;

import lombok.Getter;
import stackoverflow.global.exception.exceptionCode.ExceptionCode;

public class BusinessLogicException extends RuntimeException {
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}