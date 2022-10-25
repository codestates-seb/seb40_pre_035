package stackoverflow.global.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import stackoverflow.global.exception.dto.ErrorResponse;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse ArgumentNotVaildExceptionHandler (MethodArgumentNotValidException e) {
        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());
        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse ConstraintViolationExceptionHandler (ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());
        return response;
    }

    @ExceptionHandler
    public ResponseEntity BusinessLogicExceptionHandler (BusinessLogicException e) {
        System.out.println(e.getExceptionCode().getStatus());
        System.out.println(e.getMessage());
        return new ResponseEntity<>(HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }
}
