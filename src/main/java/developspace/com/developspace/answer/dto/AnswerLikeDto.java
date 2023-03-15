package developspace.com.developspace.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerLikeDto {
    private Boolean isLiked;
    private long likeCount;

    public AnswerLikeDto(Boolean isLiked, long likeCount) {
        this.isLiked = isLiked;
        this.likeCount = likeCount;
    }
}
