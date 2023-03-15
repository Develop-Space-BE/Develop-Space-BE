package developspace.com.developspace.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import developspace.com.developspace.common.response.success.SuccessResponse;
import developspace.com.developspace.member.dto.RequestLoginDto;
import developspace.com.developspace.member.dto.RequestSignupDto;
import developspace.com.developspace.member.service.KakaoService;
import developspace.com.developspace.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static developspace.com.developspace.common.response.success.SuccessCode.LOGIN;
import static developspace.com.developspace.common.response.success.SuccessCode.SIGNUP;

@Tag(name = "Member", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final KakaoService kakaoService;

    @Tag(name = "Member")
    @Operation(summary = "회원 가입", description = "이메일 및 닉네임 중복 확인, Password Encrypt, DB 저장")
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<Object>> signup(@RequestBody RequestSignupDto requestSignupDto){
        memberService.signup(requestSignupDto);
        return SuccessResponse.toResponseEntity(SIGNUP,null);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<Object>> login(@RequestBody RequestLoginDto requestLoginDto, HttpServletResponse response){
        memberService.login(requestLoginDto, response);
        return SuccessResponse.toResponseEntity(LOGIN, null);
    }

    @GetMapping("/kakaologin")
    public ResponseEntity<SuccessResponse<Object>> kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {
        kakaoService.kakaoLogin(code, response);
        return SuccessResponse.toResponseEntity(LOGIN, null);
    }
}