package developspace.com.developspace.common.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_AUTHORIZED(HttpStatus.OK, "접근 권한이 없는 사용자입니다.", 4031);
    private final HttpStatus httpStatus;
    private final String message;
    private final Integer customHttpStatusCode;

}
