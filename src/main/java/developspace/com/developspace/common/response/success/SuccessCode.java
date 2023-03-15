package developspace.com.developspace.common.response.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    CREATE_ANSWER(HttpStatus.OK, "답변 작성 성공", 2000),
    SIGNUP(HttpStatus.OK, "회원가입 성공", 2000),
    LOGIN(HttpStatus.OK, "로그인 성공", 2000),
    NICKNAME_UPDATE(HttpStatus.OK, "닉네임 변경 성공", 2000);

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer customHttpStatusCode;
}
