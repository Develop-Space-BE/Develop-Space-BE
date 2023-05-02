package developspace.com.developspace.answer.repository;

import developspace.com.developspace.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllBynickname(String nickname);

    Long countAllByNickname(String nickname);

    List<Answer> findByQuestionIdAndNickname(Long questionId, String nickname);

    List<Answer> findByQuestionIdAndNicknameNotOrderByLikeCountDesc(Long questionId, String nickname);

    Optional<Answer> findByAnswerAndNickname(String answer, String nickname);

}
