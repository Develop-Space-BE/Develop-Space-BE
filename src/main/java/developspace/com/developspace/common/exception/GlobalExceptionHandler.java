package developspace.com.developspace.common.exception;

import developspace.com.developspace.common.response.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException throwed at " + e.getDomain() + "_"+ e.getLayer() + " : " + e.getErrorCode());
        log.error("Cause : " + e.getCauseVariable());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidormatException(InvalidFormatException e) {
        log.error("FormatException throwed at " + e.getDomain() + "_"+ e.getLayer() + " : " + e.getErrorCode());
        log.error("Cause : " + e.getCauseVariable());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotAuthorizedMemberException(NotAuthorizedMemberException e) {
        log.error("NotAuthorizedMemberException throwed at " + e.getDomain() + "_"+ e.getLayer() + " : " + e.getErrorCode());
        log.error("Cause : " + e.getCauseVariable());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDuplicationException(DuplicationException e) {
        log.error("DuplicationException throwed at " + e.getDomain() + "_"+ e.getLayer() + " : " + e.getErrorCode());
        log.error("Cause : " + e.getCauseVariable());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

}
