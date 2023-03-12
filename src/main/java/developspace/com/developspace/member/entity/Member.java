package developspace.com.developspace.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String profileImg;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRole role = MemberRole.MEMBER;

    @Column(columnDefinition = "boolean default false")
    private Boolean quit;

    @Builder
    public Member(String email, String nickname, String password, String profileImg, MemberRole role, Boolean quit){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImg = profileImg;
        this.role = role;
        this.quit = quit;
    }
}
