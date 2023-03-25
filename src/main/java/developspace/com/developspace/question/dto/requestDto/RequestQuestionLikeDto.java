package developspace.com.developspace.question.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Bookmark 요청 DTO")
public class RequestQuestionLikeDto {
    @Schema(description = "Bookmark 되어있는지 여부", required = true, example = "false")
    private Boolean isLiked;
}