package developspace.com.developspace.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "회원 가입 요청 Dto")
@Getter
public class RequestLoginDto {
    @Schema(description = "회원 이메일", required = true, example = "test01@test.com")
    String email;
    @Schema(description = "회원 비밀번호", required = true, example = "qwe123!@#")
    String password;
    @Builder
    public RequestLoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
