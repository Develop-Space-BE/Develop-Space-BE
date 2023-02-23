package developspace.com.developspace.answer.controller;

import developspace.com.developspace.common.response.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static developspace.com.developspace.common.response.success.SuccessCode.CREATE_ANSWER;

@Tag(name = "Answer", description = "답변 관련 API")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AnswerController {
//    @Tag(name = "CollaboRequest")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "2000", description = "답변 작성 성공"),
//    })
//    @Operation(summary = "답변 작성")
//    @GetMapping("/api/answer")
//    public ResponseEntity<SuccessResponse<Object>> getCollaboRequest(@AuthenticationPrincipal ){
//
//        return SuccessResponse.toResponseEntity(CREATE_ANSWER,answerService.createAnswer());
//    }
}
