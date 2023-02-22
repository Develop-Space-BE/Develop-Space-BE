package developspace.com.developspace.common.response.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    CREATE_ANSWER(HttpStatus.OK, "답변 작성 성공", 2000);

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer customHttpStatusCode;
}
