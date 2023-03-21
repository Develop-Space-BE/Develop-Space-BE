package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl;

import developspace.com.developspace.question.entity.Category;
import developspace.com.developspace.question.entity.Question;
import developspace.com.developspace.question.entity.SubCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionRepositoryImplTest {
    @Autowired
    private QuestionRepositoryImpl questionRepository;

    @Test
    @DisplayName("질문 생성 및 DB 저장")
    void saveQuestion() {
        Question question = new Question("테스트 문제", Category.BACKEND, SubCategory.SPRING);
        Question savedQuestion = questionRepository.save(question);

        Assertions.assertThat(question).isSameAs(savedQuestion);
        Assertions.assertThat(question.getId()).isNotNull();
        Assertions.assertThat(question.getContent()).isEqualTo(savedQuestion.getContent());
        Assertions.assertThat(question.getCategory()).isEqualTo(savedQuestion.getCategory());
        Assertions.assertThat(question.getSubcategory()).isEqualTo(savedQuestion.getSubcategory());
    }

    @Test
    @DisplayName("소분류에 따른 문제 조회")
    void getQuestions() {
        Question question1 = questionRepository.save(new Question("테스트 문제 1", Category.BACKEND, SubCategory.SPRING));
        questionRepository.save(new Question("테스트 문제 2", Category.FRONTEND, SubCategory.ANGULAR));

        Assertions.assertThat(questionRepository.findAllBySubCategory(SubCategory.SPRING).get(0).getCategory())
                .isEqualTo(question1.getCategory());
    }

    @Test
    @DisplayName("문제 삭제")
    void deleteQuestions() {
        Question question1 = questionRepository.save(new Question("테스트 문제 1", Category.BACKEND, SubCategory.SPRING));
        questionRepository.deleteById(question1.getId());

        Assertions.assertThat(questionRepository.findAllBySubCategory(SubCategory.SPRING)).isEmpty();
    }
}