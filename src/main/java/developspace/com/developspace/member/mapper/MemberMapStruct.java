package developspace.com.developspace.member.mapper;

import developspace.com.developspace.answer.dto.AnswerListDto;
import developspace.com.developspace.answer.entity.Answer;
import developspace.com.developspace.member.dto.KakaoMemberDto;
import developspace.com.developspace.member.dto.RequestSignupDto;
import developspace.com.developspace.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapStruct {
    MemberMapStruct MEMBER_MAPPER = Mappers.getMapper(MemberMapStruct.class);

    Member RequestSignupDtotoMember(RequestSignupDto requestSignupDto);
    default KakaoMemberDto memberDto(Member member) {
        return KakaoMemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .build();

    }
}
