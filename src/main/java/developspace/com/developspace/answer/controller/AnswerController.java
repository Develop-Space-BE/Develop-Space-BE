package developspace.com.developspace.answer.controller;

import developspace.com.developspace.answer.dto.AnswerLikeDto;
import developspace.com.developspace.answer.dto.RequestAnswerDto;
import developspace.com.developspace.answer.service.AnswerService;
import developspace.com.developspace.common.response.success.SuccessResponse;
import developspace.com.developspace.member.entity.Member;
import developspace.com.developspace.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static developspace.com.developspace.common.response.success.SuccessCode.*;

@Tag(name = "Answer", description = "답변 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/answer")
public class AnswerController {


    private final AnswerService answerService;
    @Tag(name = "Answer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "답변 작성 성공"),
            @ApiResponse(responseCode = "4041", description = "존재하지 않는 질문입니다."),
            @ApiResponse(responseCode = "4044", description = "존재하지 않는 답변입니다.")
    })
    @Operation(summary = "답변 작성", description = "답변 작성")
    @PostMapping()
    public ResponseEntity<SuccessResponse<Object>> writeAnswer(@PathVariable Long questionId,
                                                               @RequestBody RequestAnswerDto requestAnswerDto,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        answerService.writeAnswer(questionId, requestAnswerDto, userDetails.getMember().getId());
        return SuccessResponse.toResponseEntity(WRITE_ANSWER, null);
    }

    @Tag(name = "Answer")
    @Operation(summary = "답변 수정", description = "답변 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "답변 수정 성공"),
            @ApiResponse(responseCode = "4031", description = "접근 권한이 없는 사용자입니다."),
            @ApiResponse(responseCode = "4044", description = "존재하지 않는 답변입니다.")
    })
    @PutMapping("/{answerId}")
    public ResponseEntity<SuccessResponse<Object>> updateAnswer(@PathVariable Long answerId,
                                                                 @RequestBody RequestAnswerDto requestAnswerDto,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){

        answerService.updateAnswer(answerId, requestAnswerDto.toAnswerDto(), userDetails.getMember().getId());

        return SuccessResponse.toResponseEntity(UPDATE_ANSWER, null);
    }

    @Tag(name = "Answer")
    @Operation(summary = "답변 삭제", description = "답변 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "답변 삭제 성공"),
            @ApiResponse(responseCode = "4031", description = "접근 권한이 없는 사용자입니다."),
            @ApiResponse(responseCode = "4044", description = "존재하지 않는 답변입니다.")
    })
    @DeleteMapping("/{answerId}")
    public ResponseEntity<SuccessResponse<Object>> deleteAnswer (@PathVariable Long answerId,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){

        answerService.deleteAnswer(answerId, userDetails.getMember());

        return SuccessResponse.toResponseEntity(DELETE_ANSWER, null);
    }

    @Tag(name = "Like")
    @Operation(summary = "답변 좋아요", description = "답변 좋아요")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "답변 좋아요 성공"),
            @ApiResponse(responseCode = "2000", description = "답변 좋아요 취소 성공"),
            @ApiResponse(responseCode = "4044", description = "존재하지 않는 답변입니다.")
    })
    @PostMapping("/like/{answerId}")
    public ResponseEntity<SuccessResponse<Object>> likeAnswer(@PathVariable Long answerId,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        AnswerLikeDto answerLikeDto = answerService.likeAnswer(answerId, userDetails.getMember().getNickname());

        if (answerLikeDto.getIsLiked()){
            return SuccessResponse.toResponseEntity(LIKE_ANSWER,answerLikeDto);
        }else{
            return SuccessResponse.toResponseEntity(UNLIKE_ANSWER,answerLikeDto);
        }
    }

    @Tag(name = "Answer")
    @Operation(summary = "답변 조회", description = "답변 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "답변 조회 성공"),
    })

    @GetMapping("")
    public ResponseEntity<SuccessResponse<Object>> getAnswerList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return SuccessResponse.toResponseEntity(GET_ANSWER, answerService.getAnswer(userDetails.getMember()));
    }

    @Tag(name = "Answer")
    @Operation(summary = "내 답변 조회", description = "내 답변 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "내 답변 조회 성공"),
    })

    @GetMapping("/myanswer")
    public ResponseEntity<SuccessResponse<Object>> myAnswerList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        String nickname = userDetails.getMember().getNickname();
        return SuccessResponse.toResponseEntity(MY_ANSWER, answerService.myAnswer(nickname));
    }


}
