package developspace.com.developspace.answer.mapper;

import developspace.com.developspace.answer.dto.AnswerListDto;
import developspace.com.developspace.answer.dto.MyAnswerListDto;
import developspace.com.developspace.answer.dto.RequestAnswerDto;
import developspace.com.developspace.answer.entity.Answer;
import developspace.com.developspace.member.entity.Member;
import developspace.com.developspace.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnswerMapStruct {
    AnswerMapStruct ANSWER_MAPPER = Mappers.getMapper(AnswerMapStruct.class);
    default Answer answerDtoToAnswer(RequestAnswerDto requestAnswerDto, Member member, Question question) {
        return Answer.builder()
                .nickname(member.getNickname())
                .answer(requestAnswerDto.getAnswer())
                .likeCount(0L)
                .question(question)
                .build();
    }

    default AnswerListDto answerDtoToAnswerList(Answer answer, Member member, Boolean isLiked) {
        return AnswerListDto.builder()
                .answer(answer)
                .isLiked(isLiked)
                .build();
    }

    default MyAnswerListDto answerDtoToMyAnswerList(Answer answer) {
        return MyAnswerListDto.builder()
                .answer(answer)
                .question(answer.getQuestion())
                .build();
    }
}
