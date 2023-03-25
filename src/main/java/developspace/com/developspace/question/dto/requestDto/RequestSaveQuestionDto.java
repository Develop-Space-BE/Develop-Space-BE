package developspace.com.developspace.question.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "문제 작성 요청 DTO")
@Getter
public class RequestSaveQuestionDto {
    @Schema(description = "질문 내용", required = true, example = "질문 내용")
    private String content;
    @Schema(description = "대분류", required = true, example = "ATTITUDE / FRONTEND / BACKEND")
    private String category;
    @Schema(description = "소분류", required = true, example = "ATTITUDE / REACT / VUE / ANGULAR / SPRING / NODE / FASTAPI / DRF")
    private String subcategory;
}
