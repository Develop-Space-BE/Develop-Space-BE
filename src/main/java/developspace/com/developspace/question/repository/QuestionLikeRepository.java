package developspace.com.developspace.question.repository;

import developspace.com.developspace.question.entity.QuestionLike;
import developspace.com.developspace.question.entity.QuestionLikeCompositeKey;

public interface QuestionLikeRepository {
    QuestionLike save(QuestionLike questionLike);
    void deleteById(QuestionLikeCompositeKey compositeKey);
    Boolean existsById(QuestionLikeCompositeKey compositeKey);
}
