package developspace.com.developspace.answer.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class AnswerLikeCompositeKey implements Serializable {
    @Column(nullable = false)
    private Long memberId;
    @Column(nullable = false)
    private Long answerId;

    public AnswerLikeCompositeKey(Long memberId, Long answerId) {
        this.memberId = memberId;
        this.answerId = answerId;
    }
}
