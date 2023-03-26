package developspace.com.developspace.question.repository;

import developspace.com.developspace.question.entity.Category;
import developspace.com.developspace.question.entity.Question;
import developspace.com.developspace.question.entity.SubCategory;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    Question save(Question question);
    Optional<Question> findById(Long id);
    void deleteById(Long questionId);
    List<Question> findAllBySubCategory(SubCategory subCategory);
    List<Question> findAllByCategory(Category category);
}
