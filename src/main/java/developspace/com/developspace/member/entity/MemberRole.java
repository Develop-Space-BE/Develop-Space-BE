package developspace.com.developspace.member.entity;

import lombok.Getter;

@Getter
public enum MemberRole {

    ADMIN(Authority.ADMIN),
    MEMBER(Authority.MEMBER);

    private final String authority;

    MemberRole(String authority){
        this.authority = authority;
    }

    public static class Authority {
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String MEMBER = "ROLE_MEMBER";
    }
}
