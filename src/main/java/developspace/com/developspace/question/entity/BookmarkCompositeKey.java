package developspace.com.developspace.question.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkCompositeKey implements Serializable {
    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long questionId;
}
