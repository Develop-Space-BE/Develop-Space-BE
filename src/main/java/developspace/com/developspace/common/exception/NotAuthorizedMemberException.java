package developspace.com.developspace.common.exception;

import developspace.com.developspace.common.response.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotAuthorizedMemberException {
    private Domain domain;
    private Layer layer;
    private ErrorCode errorCode;
    private Object causeVariable;
}
