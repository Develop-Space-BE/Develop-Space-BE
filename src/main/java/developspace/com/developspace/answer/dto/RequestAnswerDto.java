package developspace.com.developspace.answer.dto;

import lombok.Getter;


@Getter
public class RequestAnswerDto {
    private String answer;

    public AnswerDto toAnswerDto() {
        return AnswerDto.builder()
                .answer(answer)
                .build();
    }
}
