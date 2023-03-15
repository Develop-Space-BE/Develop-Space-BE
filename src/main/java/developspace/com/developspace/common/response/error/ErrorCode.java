package developspace.com.developspace.common.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_AUTHORIZED(HttpStatus.OK, "접근 권한이 없는 사용자입니다.", 4031),
    MEMBER_NOT_FOUND(HttpStatus.OK, "존재하지 않는 사용자입니다.", 4041),
    EMAIL_INVALID(HttpStatus.OK, "유효하지 않은 이메일입니다.", 4001),
    PASSWORD_INVALID(HttpStatus.OK, "비밀번호가 일치하지 않습니다.", 4001),
    NICKNAME_DUPLICATED(HttpStatus.OK, "중복된 닉네임입니다.", 4091),
    EMAIL_DUPLICATED(HttpStatus.OK, "중복된 이메일입니다.", 4091),
    UNHANDLED_SERVER_ERROR(HttpStatus.OK, "서버 에러 입니다.", 5000),
    ACCESSTOKEN_NOT_EXIST(HttpStatus.OK, "Token이 존재하지 않습니다.", 4010),
    ANSWER_NOT_FOUND(HttpStatus.OK, "존재하지 않는 답변입니다.", 4044),
    QUESTION_NOT_FOUND(HttpStatus.OK, "존재하지 않는 질문입니다.", 4044);

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer customHttpStatusCode;

}
