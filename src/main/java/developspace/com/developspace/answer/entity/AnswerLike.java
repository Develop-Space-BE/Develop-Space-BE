package developspace.com.developspace.answer.entity;

import developspace.com.developspace.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class AnswerLike {
    @EmbeddedId
    private AnswerLikeCompositeKey id;
    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @MapsId("answerId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    public AnswerLike(AnswerLikeCompositeKey id, Member member, Answer answer) {
        this.id = id;
        this.member = member;
        this.answer = answer;
    }
}
