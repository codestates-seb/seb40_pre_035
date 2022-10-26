package stackoverflow.global.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import stackoverflow.global.exception.dto.ErrorResponse;

@RestControllerAdvice
public class ExceptionAdvice {

    // 에러 발생시, 에러 구분 없이 에러 메세지가 가도록 통합했습니다.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> ExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(400, e.getClass().getSimpleName(), e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // 비즈니스 로직 예외
    @ExceptionHandler
    public ResponseEntity BusinessLogicExceptionHandler (BusinessLogicException e) {
        String message = e.getExceptionCode().getMessage();
        return new ResponseEntity<>(message, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}