package developspace.com.developspace.answer.mapper;

import developspace.com.developspace.answer.dto.RequestAnswerDto;
import developspace.com.developspace.answer.entity.Answer;
import developspace.com.developspace.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnswerMapStruct {
    AnswerMapStruct ANSWER_MAPPER = Mappers.getMapper(AnswerMapStruct.class);
    default Answer answerDtoToAnswer(RequestAnswerDto requestAnswerDto, Member member) {
        return Answer.builder()
                .nickname(member.getNickname())
                .answer(requestAnswerDto.getAnswer())
                .likeCount(0L)
                .build();
    }
}
