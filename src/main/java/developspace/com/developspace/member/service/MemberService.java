package developspace.com.developspace.member.service;

import developspace.com.developspace.answer.repository.AnswerRepository;
import developspace.com.developspace.common.exception.DuplicationException;
import developspace.com.developspace.common.exception.InvalidFormatException;
import developspace.com.developspace.common.exception.NotAuthorizedMemberException;
import developspace.com.developspace.common.exception.NotFoundException;
import developspace.com.developspace.jwt.JwtUtil;
import developspace.com.developspace.member.dto.KakaoMemberDto;
import developspace.com.developspace.member.dto.RequestLoginDto;
import developspace.com.developspace.member.dto.RequestSignupDto;
import developspace.com.developspace.member.entity.Member;
import developspace.com.developspace.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

import java.util.regex.Pattern;

import static developspace.com.developspace.common.exception.Domain.MEMBER;
import static developspace.com.developspace.common.exception.Layer.SERVICE;
import static developspace.com.developspace.common.response.error.ErrorCode.*;
import static developspace.com.developspace.member.mapper.MemberMapStruct.MEMBER_MAPPER;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;

    public void signup(RequestSignupDto requestSignupDto) {
        memberRepository.findByEmail(requestSignupDto.getEmail())
                .ifPresent(member -> {
                    throw new DuplicationException(MEMBER, SERVICE, EMAIL_DUPLICATED, "이메일: " + requestSignupDto.getEmail());
                });

        memberRepository.findByNickname(requestSignupDto.getNickname())
                .ifPresent(member -> {
                    throw new DuplicationException(MEMBER, SERVICE, NICKNAME_DUPLICATED, "닉네임: " + requestSignupDto.getNickname());
                });

        if(!isValidEmail(requestSignupDto.getEmail())){
            throw new InvalidFormatException(MEMBER, SERVICE, EMAIL_INVALID, "이메일: " + requestSignupDto.getEmail());
        }

        if(!isValidPassword(requestSignupDto.getPassword())){
            throw new InvalidFormatException(MEMBER, SERVICE, PASSWORD_INVALID, "이메일: " + requestSignupDto.getEmail());
        }

        requestSignupDto.encryptPassword(passwordEncoder.encode(requestSignupDto.getPassword()));

        if (requestSignupDto.getProfileImg() == null){
            requestSignupDto.setDefaultProfileImg();
        }

        Member member = MEMBER_MAPPER.RequestSignupDtotoMember(requestSignupDto);
        memberRepository.save(member);
    }

    public static boolean isValidEmail(String email){
        final String REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        return Pattern.matches(REGEX, email);
    }

    public static boolean isValidPassword(String password){
        final int MAX = 15;
        final int MIN = 8;

        final String REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{" + MIN + "," + MAX + "}$";

        return Pattern.matches(REGEX, password);
    }

    @Transactional
    public void login(RequestLoginDto requestLoginDto, HttpServletResponse response){
        Member member = memberRepository.findByEmail(requestLoginDto.getEmail()).orElseThrow(
                () -> new NotFoundException(MEMBER, SERVICE, MEMBER_NOT_FOUND, "이메일: " + requestLoginDto.getEmail())
        );

        if(!passwordEncoder.matches(requestLoginDto.getPassword(), member.getPassword())){
            throw new NotAuthorizedMemberException(MEMBER, SERVICE, PASSWORD_INVALID, "이메일: " + member.getEmail());
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getEmail(),member.getRole()));
    }

    @Transactional
    public void updateNickname(String nickname, Member member) {
//        String nicknameBefore = member.getNickname();
        memberRepository.findByNickname(nickname)
                        .ifPresent(n -> {
                            throw new DuplicationException(MEMBER, SERVICE, NICKNAME_DUPLICATED, "이메일: " + member.getEmail());
        });
        
//        answerRepository.updateNickname(nicknameBefore, nickname);

        member.updateNickname(nickname);
        memberRepository.save(member);
    }

    public KakaoMemberDto memberInfo(Member member) {
        return MEMBER_MAPPER.memberDto(member);
    }
}
