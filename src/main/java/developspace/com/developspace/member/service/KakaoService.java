package developspace.com.developspace.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import developspace.com.developspace.jwt.JwtUtil;
import developspace.com.developspace.member.dto.KakaoMemberDto;
import developspace.com.developspace.member.entity.Member;
import developspace.com.developspace.member.entity.MemberRole;
import developspace.com.developspace.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@PropertySource("classpath:application-OAuth.properties")
public class KakaoService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.redirect.uri}")
    private String kakaoRedirectUri;

    public void kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        String accessToken = getToken(code);
        KakaoMemberDto kakaoMemberDto = getKakaoMemberInfo(accessToken);
        Member kakaoMember = registerKakaoMemberIfNeeded(kakaoMemberDto);
        String createToken =  jwtUtil.createToken(kakaoMember.getEmail(), MemberRole.MEMBER);

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, createToken);
    }

    private String getToken(String code) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoClientId);
        body.add("redirect_uri", kakaoRedirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private KakaoMemberDto getKakaoMemberInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoMemberInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoMemberInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        System.out.println(responseBody);
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
                .get("email").asText();
        String profileImg = jsonNode.get("properties")
                .get("profile_image").asText();

        return new KakaoMemberDto(id, email, nickname, profileImg);
    }

    private Member registerKakaoMemberIfNeeded(KakaoMemberDto kakaoMemberDto) {

        Member kakaoMember = memberRepository.findByEmail(kakaoMemberDto.getEmail())
                .orElse(null);
        if (kakaoMember == null) {
            String kakaoEmail = kakaoMemberDto.getEmail();
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);

            kakaoMember = Member.builder()
                    .email(kakaoEmail)
                    .nickname(kakaoMemberDto.getNickname())
                    .password(encodedPassword)
                    .profileImg(kakaoMemberDto.getProfileImg())
                    .role(MemberRole.MEMBER)
                    .quit(false)
                    .build();

            memberRepository.save(kakaoMember);
        }
        return kakaoMember;
    }




}