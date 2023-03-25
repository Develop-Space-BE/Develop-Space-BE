package developspace.com.developspace.question.service;

import developspace.com.developspace.common.exception.NotAuthorizedMemberException;

import developspace.com.developspace.common.exception.NotFoundException;
import developspace.com.developspace.member.entity.MemberRole;
import developspace.com.developspace.question.dto.requestDto.RequestSaveQuestionDto;
import developspace.com.developspace.question.dto.responseDto.ResponseQuestionDto;
import developspace.com.developspace.question.entity.*;
import developspace.com.developspace.question.repository.BookmarkRepository;
import developspace.com.developspace.question.repository.QuestionLikeRepository;
import developspace.com.developspace.question.repository.QuestionRepository;
import developspace.com.developspace.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static developspace.com.developspace.common.response.error.ErrorCode.*;
import static developspace.com.developspace.question.mapper.QuestionMapStruct.QUESTION_MAPPER;
import static developspace.com.developspace.common.exception.Domain.QUESTION;
import static developspace.com.developspace.common.exception.Layer.SERVICE;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionLikeRepository questionLikeRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public void saveQuestion(UserDetailsImpl userDetails, RequestSaveQuestionDto requestDto) {
        if (userDetails.getMember().getRole() != MemberRole.ADMIN) {
            throw new NotAuthorizedMemberException(QUESTION, SERVICE, MEMBER_NOT_AUTHORIZED, userDetails.getMember().getNickname());
        }
        Question question = QUESTION_MAPPER.RequestSaveQuestionDtoToQuestion(requestDto);
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(UserDetailsImpl userDetails, Long questionId) {
        if (userDetails.getMember().getRole() != MemberRole.ADMIN) {
            throw new NotAuthorizedMemberException(QUESTION, SERVICE, MEMBER_NOT_AUTHORIZED, userDetails.getMember().getNickname());
        }
        questionRepository.deleteById(questionId);
    }

    @Transactional
    public void updateQuestion(UserDetailsImpl userDetails, Long questionId){
        if (userDetails.getMember().getRole() != MemberRole.ADMIN) {
            throw new NotAuthorizedMemberException(QUESTION, SERVICE, MEMBER_NOT_AUTHORIZED, userDetails.getMember().getNickname());
        }
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()-> new NotFoundException(QUESTION, SERVICE, QUESTION_NOT_FOUND, questionId));

        // update question DTO 만들어서 넣어줄 것
    }

    @Transactional
    public List<ResponseQuestionDto> getQuestionListBySubcategory(String subcategory){
        SubCategory subCategoryEnum = SubCategory.valueOf(subcategory);
        List<Question> questionList = questionRepository.findAllBySubCategory(subCategoryEnum);
        List<ResponseQuestionDto> responseQuestionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            responseQuestionDtoList.add(QUESTION_MAPPER.QuestionToResponseQuestionDto(question));
        }
        return responseQuestionDtoList;
    }

    @Transactional
    public void likeQuestion(UserDetailsImpl userDetails, Long questionId) {
        QuestionLikeCompositeKey compositeKey = new QuestionLikeCompositeKey(userDetails.getMember().getId(), questionId);
        if (questionLikeRepository.existsById(compositeKey)){
            questionLikeRepository.deleteById(compositeKey);
            return;
        }
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()-> new NotFoundException(QUESTION, SERVICE, QUESTION_NOT_FOUND, questionId));
        QuestionLike questionLike = new QuestionLike(compositeKey, userDetails.getMember(), question);
        questionLikeRepository.save(questionLike);
    }

    @Transactional
    public void bookmarkQuestion(UserDetailsImpl userDetails, Long questionId) {
        BookmarkCompositeKey compositeKey = new BookmarkCompositeKey(userDetails.getMember().getId(), questionId);
        if (bookmarkRepository.existsById(compositeKey)){
            bookmarkRepository.deleteById(compositeKey);
            return;
        }
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()-> new NotFoundException(QUESTION, SERVICE, QUESTION_NOT_FOUND, questionId));
        Bookmark bookmark = new Bookmark(compositeKey, userDetails.getMember(), question);
        bookmarkRepository.save(bookmark);
    }
}
