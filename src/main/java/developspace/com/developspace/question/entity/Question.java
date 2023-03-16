package developspace.com.developspace.question.entity;

import developspace.com.developspace.common.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Question extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SubCategory subcategory;
}
