package developspace.com.developspace.answer.repository;

import developspace.com.developspace.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByOrderByLikeCountDesc();


    List<Answer> findAllBynickname(String nickname);
}
