package developspace.com.developspace.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoMemberDto {
    private Long id;
    private String email;
    private String nickname;
    private String profileImg;
}
