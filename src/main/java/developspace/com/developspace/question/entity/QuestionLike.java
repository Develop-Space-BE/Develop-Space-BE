package developspace.com.developspace.question.entity;

import developspace.com.developspace.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionLike {
    @EmbeddedId
    private QuestionLikeCompositeKey id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @MapsId("questionId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;
}
