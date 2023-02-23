package developspace.com.developspace.question.entity;

import developspace.com.developspace.common.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Question extends Timestamped {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;


}
