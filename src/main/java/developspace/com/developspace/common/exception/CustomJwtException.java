package developspace.com.developspace.common.exception;

import developspace.com.developspace.common.response.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomJwtException extends SecurityException{
    private final ErrorCode errorCode;
}
