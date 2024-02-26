package my.home.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class GlobalControllerExceptionHandler {
//    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<AppError> handleBadRequest(BadRequestException e) {
        // Nothing to do
        return new ResponseEntity<>(
                new AppError(
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage()
                ), HttpStatus.BAD_REQUEST
        );
    }
}
