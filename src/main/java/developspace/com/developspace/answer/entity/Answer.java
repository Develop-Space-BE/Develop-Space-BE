package developspace.com.developspace.answer.entity;

import developspace.com.developspace.answer.dto.AnswerDto;
import developspace.com.developspace.common.entity.Timestamped;
import developspace.com.developspace.question.entity.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Answer extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String answer;

    @Column
    private Long likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Builder
    public Answer(Long id, String nickname, String answer, Long likeCount, Question question){
        this.id = id;
        this.nickname = nickname;
        this.answer = answer;
        this.likeCount = likeCount;
        this.question = question;

    }

    public void update(AnswerDto answerDto){
        this.answer = answerDto.getAnswer();
    }

}
