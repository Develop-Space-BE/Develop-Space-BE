package developspace.com.developspace.answer.repository;

import developspace.com.developspace.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
