package developspace.com.developspace.member.mapper;

import developspace.com.developspace.member.dto.RequestSignupDto;
import developspace.com.developspace.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapStruct {
    MemberMapStruct MEMBER_MAPPER = Mappers.getMapper(MemberMapStruct.class);

    Member RequestSignupDtotoMember(RequestSignupDto requestSignupDto);
}
