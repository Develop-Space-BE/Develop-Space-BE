package developspace.com.developspace.answer.repository;

import developspace.com.developspace.answer.entity.AnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {
    Optional<AnswerLike> findByAnswerIdAndMemberId(Long answerId, Long id);

    @Modifying
    @Query("delete from AnswerLike al where al.answer.id = :id")
    void deleteAllByAnswerId(@Param("id") Long answerId);
}
