package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl;

import developspace.com.developspace.question.entity.Category;
import developspace.com.developspace.question.entity.Question;
import developspace.com.developspace.question.entity.SubCategory;
import developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA.JPAQuestionRepository;
import developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.QueryDSL.QDSLQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements developspace.com.developspace.question.repository.QuestionRepository {
    private final JPAQuestionRepository jpaQuestionRepository;
    private final QDSLQuestionRepository qdslQuestionRepository;

    @Override
    public Question save(Question question) {
        return jpaQuestionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return jpaQuestionRepository.findById(id);
    }

    @Override
    public void deleteById(Long questionId) {
        jpaQuestionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> findAllBySubCategory(SubCategory subCategory) {
        return qdslQuestionRepository.findAllBySubCategory(subCategory);
    }

    @Override
    public List<Question> findAllByCategory(Category category) {
        return qdslQuestionRepository.findAllByCategory(category);
    }
}
