package developspace.com.developspace.member.dto;

import developspace.com.developspace.member.entity.MemberRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestSignupDto {
    private String email;
    private String nickname;
    private String password;
    private String profileImg;
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
