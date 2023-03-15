package developspace.com.developspace.answer.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AnswerDto {
    private String answer;

    @Builder
    public AnswerDto(String answer){
        this.answer = answer;
    }

}
