package developspace.com.developspace.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "카카오 회원 정보 Dto")
@Getter
@AllArgsConstructor
public class KakaoMemberDto {
    private Long id;
    @Schema(description = "회원 이메일", required = true, example = "test01@test.com")
    private String email;
    @Schema(description = "회원 닉네임", required = true, example = "test01")
    private String nickname;
    @Schema(description = "회원 프로필 사진", example = "Profile Img from S3")
    private String profileImg;
}
