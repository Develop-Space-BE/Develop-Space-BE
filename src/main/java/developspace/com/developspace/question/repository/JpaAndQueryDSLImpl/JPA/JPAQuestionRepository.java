package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA;

import developspace.com.developspace.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAQuestionRepository extends JpaRepository<Question, Long> {
    Question save(Question question);
    Optional<Question> findById(Long id);
    void deleteById(Long questionId);
    Long countBy();
}
