package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA;

import developspace.com.developspace.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAQuestionRepository extends JpaRepository<Question, Long> {
    Question save(Question question);
    void deleteById(Long questionId);
}
