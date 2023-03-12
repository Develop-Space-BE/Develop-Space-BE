package developspace.com.developspace.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestLoginDto {
    String email;
    String password;
    @Builder
    public RequestLoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
