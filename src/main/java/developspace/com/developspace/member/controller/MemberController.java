package developspace.com.developspace.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import developspace.com.developspace.common.response.success.SuccessResponse;
import developspace.com.developspace.member.dto.RequestLoginDto;
import developspace.com.developspace.member.dto.RequestSignupDto;
import developspace.com.developspace.member.dto.RequestUpdateNicknameDto;
import developspace.com.developspace.member.service.KakaoService;
import developspace.com.developspace.member.service.MemberService;
import developspace.com.developspace.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static developspace.com.developspace.common.response.success.SuccessCode.NICKNAME_UPDATE;
import static developspace.com.developspace.common.response.success.SuccessCode.SIGNUP;
import static developspace.com.developspace.common.response.success.SuccessCode.LOGIN;

@Tag(name = "Member", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final KakaoService kakaoService;

    @Tag(name = "Member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "회원 가입 성공"),
            @ApiResponse(responseCode = "4001", description = "비밀번호 오류"),
            @ApiResponse(responseCode = "4001", description = "이메일 오류"),
            @ApiResponse(responseCode = "4091", description = "이메일 중복"),
            @ApiResponse(responseCode = "4091", description = "닉네임 중복")
    })
    @Operation(summary = "회원 가입", description = "이메일 및 닉네임 중복 확인, Password Encrypt, DB 저장")
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<Object>> signup(@RequestBody RequestSignupDto requestSignupDto){
        memberService.signup(requestSignupDto);
        return SuccessResponse.toResponseEntity(SIGNUP,null);
    }

    @Tag(name = "Member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "로그인 성공"),
            @ApiResponse(responseCode = "4041", description = "회원정보 없음"),
            @ApiResponse(responseCode = "4001", description = "비밀번호 오류")
    })
    @Operation(summary = "관리자 로그인", description = "계정 정보 확인")
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<Object>> login(@RequestBody RequestLoginDto requestLoginDto, HttpServletResponse response){
        memberService.login(requestLoginDto, response);
        return SuccessResponse.toResponseEntity(LOGIN, null);
    }

    @Tag(name = "Member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "카카오 로그인 성공")
    })
    @Operation(summary = "카카오 로그인", description = "카카오 로그인")
    @GetMapping("/kakaologin")
    public ResponseEntity<SuccessResponse<Object>> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        kakaoService.kakaoLogin(code, response);
        return SuccessResponse.toResponseEntity(LOGIN, null);
    }

    @Tag(name = "Member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "2000", description = "닉네임 변경 성공"),
            @ApiResponse(responseCode = "4091", description = "중복된 닉네임")
    })
    @Operation(summary = "닉네임 변경", description = "닉네임 변경")
    @PutMapping("/setting")
    public ResponseEntity<SuccessResponse<Object>> updateNickname(@RequestBody RequestUpdateNicknameDto requestUpdateNicknameDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        memberService.updateNickname(requestUpdateNicknameDto.getNickname(), userDetails.getMember());
        return SuccessResponse.toResponseEntity(NICKNAME_UPDATE, null);
    }

}