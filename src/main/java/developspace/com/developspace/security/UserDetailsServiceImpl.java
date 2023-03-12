package developspace.com.developspace.security;

import developspace.com.developspace.common.exception.NotFoundException;
import developspace.com.developspace.member.entity.Member;
import developspace.com.developspace.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static developspace.com.developspace.common.exception.Domain.MEMBER;
import static developspace.com.developspace.common.exception.Layer.SERVICE;
import static developspace.com.developspace.common.response.error.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException(MEMBER, SERVICE, MEMBER_NOT_FOUND, "UserDetailsServiceImpl"));
        return new UserDetailsImpl(member, member.getEmail(), member.getPassword());
    }
}