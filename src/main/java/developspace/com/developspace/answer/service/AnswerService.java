package developspace.com.developspace.answer.service;

import developspace.com.developspace.answer.dto.*;
import developspace.com.developspace.answer.entity.Answer;
import developspace.com.developspace.answer.entity.AnswerLike;
import developspace.com.developspace.answer.entity.AnswerLikeCompositeKey;
import developspace.com.developspace.answer.repository.AnswerLikeRepository;
import developspace.com.developspace.answer.repository.AnswerRepository;
import developspace.com.developspace.common.exception.NotAuthorizedMemberException;
import developspace.com.developspace.common.exception.NotFoundException;
import developspace.com.developspace.member.entity.Member;
import developspace.com.developspace.member.entity.MemberRole;
import developspace.com.developspace.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static developspace.com.developspace.answer.mapper.AnswerMapStruct.ANSWER_MAPPER;
import static developspace.com.developspace.common.exception.Domain.ANSWER;
import static developspace.com.developspace.common.exception.Layer.SERVICE;
import static developspace.com.developspace.common.response.error.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerLikeRepository answerLikeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void writeAnswer(RequestAnswerDto requestAnswerDto, Long id) {
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

    @Transactional
    public void deleteAnswer(Long answerId, Member member) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new NotFoundException(ANSWER, SERVICE, ANSWER_NOT_FOUND, "Answer ID : " + answerId)
        );
        member = memberRepository.findById(member.getId())
                .orElseThrow(() -> new NotFoundException(ANSWER, SERVICE, MEMBER_NOT_FOUND, "Id : " ));

        MemberRole memberRole = member.getRole();

        if(memberRole == MemberRole.MEMBER){
            if (!answer.getNickname().equals(member.getNickname())){
                throw new NotAuthorizedMemberException(ANSWER, SERVICE, MEMBER_NOT_AUTHORIZED, member.getNickname());
            }
        } else {
            answerLikeRepository.deleteAllByAnswerId(answerId);
            answerRepository.deleteById(answerId);
        }

        answerLikeRepository.deleteAllByAnswerId(answerId);
        answerRepository.deleteById(answerId);
    }

    @Transactional
    public AnswerLikeDto likeAnswer(Long answerId, String nickname) {

        boolean isLiked = false;

        Member member = memberRepository.findByNickname(nickname).orElseThrow(
                () -> new NotFoundException(ANSWER, SERVICE, MEMBER_NOT_FOUND, "Nickname : " + nickname)
        );
        Answer answer = answerRepository.findById(answerId).orElseThrow(
                () -> new NotFoundException(ANSWER, SERVICE, ANSWER_NOT_FOUND, "Answer ID : " + answerId)
        );

        AnswerLikeCompositeKey answerLikeCompositeKey = new AnswerLikeCompositeKey(answerId, member.getId());
        Optional<AnswerLike> like = answerLikeRepository.findByAnswerIdAndMemberId(answerId,member.getId());
        if (like.isPresent()){
            AnswerLike answerLike = like.get();
            answer.unlike();
            answerLikeRepository.delete(answerLike);

            return new AnswerLikeDto(isLiked, answer.getLikeCount());
        }
        AnswerLike answerLike = new AnswerLike(answerLikeCompositeKey, member, answer);
        answer.like();
        answerLikeRepository.save(answerLike);
        isLiked = true;

        return new AnswerLikeDto(isLiked, answer.getLikeCount());

    }

    @Transactional
    public List<AnswerListDto> getAnswer(Member member) {
        List<Answer> answerList = answerRepository.findAllByOrderByLikeCountDesc();
        List<AnswerListDto> answerLists = new ArrayList<>();

        for(Answer answer : answerList) {
            boolean answerIsLiked = false;
            Optional<AnswerLike> answerLiked = answerLikeRepository.findByAnswerIdAndMemberId(answer.getId(), member.getId());
            if (answerLiked.isPresent()) {
                answerIsLiked = true;
            }
            AnswerListDto answerListDto = ANSWER_MAPPER.answerDtoToAnswerList(answer, member, answerIsLiked);
            answerLists.add(answerListDto);
        }

        return answerLists;
    }


    public MyAnswerListDtos myAnswer(String nickname) {
        List<Answer> myAnswerList = answerRepository.findAllBynickname(nickname);
        List<MyAnswerListDto> myAnswerLists = new ArrayList<>();

        for(Answer answer : myAnswerList) {
            MyAnswerListDto myAnswerListDto =  ANSWER_MAPPER.answerDtoToMyAnswerList(answer);
            myAnswerLists.add(myAnswerListDto);
        }

        MyAnswerListDtos myAnswerListDtos = new MyAnswerListDtos(myAnswerLists);
        return myAnswerListDtos;
    }
}
