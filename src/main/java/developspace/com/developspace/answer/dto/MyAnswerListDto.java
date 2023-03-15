package developspace.com.developspace.answer.dto;

import developspace.com.developspace.answer.entity.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "내 답변 리스트 조회 Dto")
@Getter
public class MyAnswerListDto {
    @Schema(description = "답변 번호", example = "답변 번호")
    private Long id;
    @Schema(description = "유저 닉네임", example = "유저 닉네임")
    private String nickname;
    @Schema(description = "답변 내용", example = "답변 내용")
    private String answer;

    @Builder
    public MyAnswerListDto(Answer answer){
        this.id = answer.getId();
        this.nickname = answer.getNickname();
        this.answer = answer.getAnswer();
    }


}
