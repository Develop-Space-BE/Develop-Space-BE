package developspace.com.developspace.question.controller;

import developspace.com.developspace.common.response.success.SuccessCode;
import developspace.com.developspace.common.response.success.SuccessResponse;
import developspace.com.developspace.question.dto.requestDto.RequestSaveQuestionDto;
import developspace.com.developspace.question.dto.responseDto.ResponseQuestionDto;
import developspace.com.developspace.question.service.QuestionService;
import developspace.com.developspace.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Question", description = "질문 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {
    private final QuestionService questionService;

    @Tag(name = "Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "질문 작성 성공")
    })
    @Operation(summary = "질문 작성", description = "질문 작성 API, 관리자 계정만 사용 가능")
    @PostMapping()
    public ResponseEntity<SuccessResponse<Object>> createQuestion(@RequestBody RequestSaveQuestionDto requestDto,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        questionService.saveQuestion(userDetails, requestDto);
        return SuccessResponse.toResponseEntity(SuccessCode.WRITE_QUESTION, null);
    }

    @Tag(name = "Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "질문 삭제 성공")
    })
    @Operation(summary = "질문 삭제", description = "질문 삭제 API, 관리자 계정만 사용 가능")
    @DeleteMapping("/{questionId}}")
    public ResponseEntity<SuccessResponse<Object>> deleteQuestion(@PathVariable Long questionId,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        questionService.deleteQuestion(userDetails, questionId);
        return SuccessResponse.toResponseEntity(SuccessCode.DELETE_QUESTION, null);
    }

    @Tag(name = "Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "질문 조회 성공")
    })
    @Operation(summary = "소분류에 따른 질문 조회", description = "소분류에 따른 질문 조회 API")
    @GetMapping("/subcategory/{subcategory}")
    public ResponseEntity<SuccessResponse<List<ResponseQuestionDto>>> getQuestionBySubcategory(@PathVariable String subcategory) {
        return SuccessResponse.toResponseEntity(SuccessCode.GET_QUESTION_BY_SUBCATEGORY, questionService.getQuestionListBySubcategory(subcategory));
    }

    @Tag(name = "Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "질문 조회 성공")
    })
    @Operation(summary = "대분류에 따른 질문 조회", description = "소분류에 따른 질문 조회 API")
    @GetMapping("/category/{category}")
    public ResponseEntity<SuccessResponse<List<ResponseQuestionDto>>> getQuestionByCategory(@PathVariable String category) {
        return SuccessResponse.toResponseEntity(SuccessCode.GET_QUESTION_BY_SUBCATEGORY, questionService.getQuestionListByCategory(category));
    }

    @Tag(name = "Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "질문 좋아요 성공")
    })
    @Operation(summary = "질문 좋아요", description = "질문 좋아요 API")
    @PostMapping("/like/{questionId}")
    public ResponseEntity<SuccessResponse<Object>> likeQuestion(@PathVariable Long questionId,
                                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        questionService.likeQuestion(userDetails, questionId);
        return SuccessResponse.toResponseEntity(SuccessCode.LIKE_QUESTION, null);
    }

    @Tag(name = "Question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "질문 좋아요 성공")
    })
    @Operation(summary = "질문 북마크", description = "질문 좋아요 API")
    @PostMapping("/bookmark/{questionId}")
    public ResponseEntity<SuccessResponse<Object>> bookmarkQuestion(@PathVariable Long questionId,
                                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        questionService.bookmarkQuestion(userDetails, questionId);
        return SuccessResponse.toResponseEntity(SuccessCode.LIKE_QUESTION, null);
    }
}
