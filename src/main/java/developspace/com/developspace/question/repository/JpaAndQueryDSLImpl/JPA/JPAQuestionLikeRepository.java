package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA;

import developspace.com.developspace.question.entity.QuestionLike;
import developspace.com.developspace.question.entity.QuestionLikeCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAQuestionLikeRepository extends JpaRepository<QuestionLike, Long> {
    QuestionLike save(QuestionLike questionLike);
    void deleteById(QuestionLikeCompositeKey compositeKey);
    Boolean existsById(QuestionLikeCompositeKey compositeKey);
}
