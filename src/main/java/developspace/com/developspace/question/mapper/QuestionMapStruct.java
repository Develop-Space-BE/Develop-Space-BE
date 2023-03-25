package developspace.com.developspace.question.mapper;

import developspace.com.developspace.question.dto.requestDto.RequestSaveQuestionDto;
import developspace.com.developspace.question.dto.responseDto.ResponseQuestionDto;
import developspace.com.developspace.question.entity.Category;
import developspace.com.developspace.question.entity.Question;
import developspace.com.developspace.question.entity.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface QuestionMapStruct {
    QuestionMapStruct QUESTION_MAPPER = Mappers.getMapper(QuestionMapStruct.class);

    default Question RequestSaveQuestionDtoToQuestion(RequestSaveQuestionDto requestDto) {
        return Question.builder()
                .content(requestDto.getContent())
                .category(Category.valueOf(requestDto.getCategory()))
                .subcategory(SubCategory.valueOf(requestDto.getSubcategory()))
                .build();
    }

    default ResponseQuestionDto QuestionToResponseQuestionDto(Question question) {
        return ResponseQuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .category(question.getCategory().name())
                .subcategory(question.getSubcategory().name())
                .build();
    }
}
