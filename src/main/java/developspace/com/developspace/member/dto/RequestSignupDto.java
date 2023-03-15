package developspace.com.developspace.member.dto;

import developspace.com.developspace.member.entity.MemberRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "회원 가입 요청 Dto")
@Getter
public class RequestSignupDto {
    @Schema(description = "회원 이메일", required = true, example = "test01@test.com")
    private String email;
    @Schema(description = "회원 닉네임", required = true, example = "test01")
    private String nickname;
    @Schema(description = "회원 비밀번호", required = true, example = "qwe123!@#")
    private String password;
    @Schema(description = "회원 프로필 사진", example = "Profile Img from S3")
    private String profileImg;
    @Schema(description = "회원 등급", example = "ADMIN")
    private MemberRole role;
    private Boolean quit;

    @Builder
    public RequestSignupDto(String email, String nickname, String password, String profileImg, MemberRole role){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImg = profileImg;
        this.role = role;
        this.quit = false;
    }

    public void setDefaultProfileImg(){
        this.profileImg = "https://user-images.githubusercontent.com/117705848/224546329-0b992dc5-bfba-4140-b157-e87e564c3240.png";
    }

    public void encryptPassword(String encryptedPassword){
        this.password = encryptedPassword;
    }
}
