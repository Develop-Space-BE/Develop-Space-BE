package developspace.com.developspace.question.repository;

import developspace.com.developspace.question.entity.Question;
import developspace.com.developspace.question.entity.SubCategory;

import java.util.List;

public interface QuestionRepository {
    Question save(Question question);
    void deleteById(Long questionId);
    List<Question> findAllBySubCategory(SubCategory subCategory);
}
