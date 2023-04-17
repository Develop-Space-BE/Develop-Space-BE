package developspace.com.developspace.question.entity;

import developspace.com.developspace.common.entity.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Question extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SubCategory subcategory;

    @Builder
    public Question(String content, Category category, SubCategory subcategory) {
        this.content = content;
        this.category = category;
        this.subcategory = subcategory;
    }
}
