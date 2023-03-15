package developspace.com.developspace.answer.service;

import developspace.com.developspace.answer.dto.AnswerDto;
import developspace.com.developspace.answer.dto.RequestAnswerDto;
import developspace.com.developspace.answer.entity.Answer;
import developspace.com.developspace.answer.repository.AnswerRepository;
import developspace.com.developspace.common.exception.NotAuthorizedMemberException;
import developspace.com.developspace.common.exception.NotFoundException;
import developspace.com.developspace.member.entity.Member;
import developspace.com.developspace.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static developspace.com.developspace.answer.mapper.AnswerMapStruct.ANSWER_MAPPER;
import static developspace.com.developspace.common.exception.Domain.ANSWER;
import static developspace.com.developspace.common.exception.Layer.SERVICE;
import static developspace.com.developspace.common.response.error.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void writeAnswer(Long questionId, RequestAnswerDto requestAnswerDto, Long id) {
//        Question question = questionRepository.findById(questionIdId).orElseThrow(
//                () -> new NotFoundException(ANSWER, SERVICE, QUESTION_NOT_FOUND, "Question ID : " + questionId)
//        );

    Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ANSWER, SERVICE, MEMBER_NOT_FOUND, "Id : " + id));

    Answer answer = ANSWER_MAPPER.answerDtoToAnswer(requestAnswerDto, member);
    answerRepository.save(answer);
    }

    @Transactional
    public void updateAnswer(Long answerId, AnswerDto answerDto, Long id) {

        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new NotFoundException(ANSWER, SERVICE, ANSWER_NOT_FOUND, "Answer ID : " + answerId)
        );

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ANSWER, SERVICE, MEMBER_NOT_FOUND, "Id : " + id));

        if (!answer.getNickname().equals(member.getNickname())){
            throw new NotAuthorizedMemberException(ANSWER, SERVICE, MEMBER_NOT_AUTHORIZED, member.getNickname());
        }

        answer.update(answerDto);
        answerRepository.save(answer);
    }

}
