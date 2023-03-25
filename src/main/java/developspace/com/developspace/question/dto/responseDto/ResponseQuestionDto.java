package developspace.com.developspace.question.dto.responseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "질문 리스트 출력 Dto")
@Getter
@Builder
public class ResponseQuestionDto {
    @Schema(description = "질문 id", example = "23")
    private Long id;
    @Schema(description = "질문 내용", example = "의존성 주입에 대해 설명해보세요.")
    private String content;
    @Schema(description = "대분류", example ="SPRING")
    private String category;
    @Schema(description = "소분류", example ="SPRING")
    private String subcategory;
}
