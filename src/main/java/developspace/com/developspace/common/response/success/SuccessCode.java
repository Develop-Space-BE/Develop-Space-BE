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
    NICKNAME_UPDATE(HttpStatus.OK, "닉네임 변경 성공", 2000),
    WRITE_ANSWER(HttpStatus.OK, "답변 작성 성공", 2000),
    UPDATE_ANSWER(HttpStatus.OK, "답변 수정 성공", 2000),
    DELETE_ANSWER(HttpStatus.OK, "답변 삭제 성공", 2000),
    LIKE_ANSWER(HttpStatus.OK, "답변 좋아요 성공", 2000),
    UNLIKE_ANSWER(HttpStatus.OK, "답변 좋아요 취소 성공", 2000),
    GET_ANSWER(HttpStatus.OK, "답변 조회 성공", 2000),
    MY_ANSWER(HttpStatus.OK, "내 답변 조회 성공", 2000),
    WRITE_QUESTION(HttpStatus.OK, "질문 작성 성공", 2000),
    GET_QUESTION_BY_SUBCATEGORY(HttpStatus.OK, "소분류에 따른 질문 조회 성공", 2000),
    UPDATE_QUESTION(HttpStatus.OK, "질문 수정 성공", 2000),
    DELETE_QUESTION(HttpStatus.OK, "질문 삭제 성공", 2000),
    LIKE_QUESTION(HttpStatus.OK, "질문 좋아요 성공", 2000),
    BOOKMARK_QUESTION(HttpStatus.OK, "질문 북마크 성공", 2000),
    GET_BOOKMARKED_QUESTION(HttpStatus.OK, "북마크한 질문 조회 성공", 2000);


    private final HttpStatus httpStatus;
    private final String message;
    private final Integer customHttpStatusCode;
}
