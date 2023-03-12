package developspace.com.developspace.common.exception;

import developspace.com.developspace.common.response.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends IllegalArgumentException{
    private ErrorCode errorCode;
}
