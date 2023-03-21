package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl;

import developspace.com.developspace.question.entity.QuestionLike;
import developspace.com.developspace.question.entity.QuestionLikeCompositeKey;
import developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA.JPAQuestionLikeRepository;
import developspace.com.developspace.question.repository.QuestionLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionLikeRepositoryImpl implements QuestionLikeRepository {
    private final JPAQuestionLikeRepository jpaQuestionLikeRepository;

    @Override
    public QuestionLike save(QuestionLike questionLike) {
        return jpaQuestionLikeRepository.save(questionLike);
    }

    @Override
    public void deleteById(QuestionLikeCompositeKey compositeKey) {
        jpaQuestionLikeRepository.deleteById(compositeKey);
    }

    @Override
    public Boolean existsById(QuestionLikeCompositeKey compositeKey) {
        return jpaQuestionLikeRepository.existsById(compositeKey);
    }
}
