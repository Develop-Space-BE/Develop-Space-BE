package developspace.com.developspace.answer.dto;

import developspace.com.developspace.answer.entity.Answer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "답변 리스트 조회 Dto")
@Getter
public class AnswerListDto {

    @Schema(description = "답변 번호", example = "답변 번호")
    private Long id;
    @Schema(description = "유저 닉네임", example = "유저 닉네임")
    private String nickname;
    @Schema(description = "답변 내용", example = "답변 내용")
    private String answer;
    @Schema(description = "질문 내용", example = "질문 내용")
    private String content;
    @Schema(description = "좋아요 갯수", example = "좋아요 갯수")
    private Long likeCount;
    @Schema(description = "좋아요 여부", example = "좋아요 여부")
    private boolean isLiked;

    @Builder
    public AnswerListDto(Answer answer, boolean isLiked, Question question){
        this.id = answer.getId();
        this.nickname = answer.getNickname();
        this.answer = answer.getAnswer();
        this.content = question.getContent();
        this.likeCount = answer.getLikeCount();
        this.isLiked = isLiked;
    }

}
