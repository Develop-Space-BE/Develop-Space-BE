package developspace.com.developspace.question.repository.JpaAndQueryDSLImpl;

import developspace.com.developspace.question.entity.Question;
import developspace.com.developspace.question.entity.SubCategory;
import developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.JPA.JPAQuestionRepository;
import developspace.com.developspace.question.repository.JpaAndQueryDSLImpl.QueryDSL.QDSLQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void deleteById(Long questionId) {
        jpaQuestionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> findAllBySubCategory(SubCategory subCategory) {
        return qdslQuestionRepository.findAllBySubCategory(subCategory);
    }
}
