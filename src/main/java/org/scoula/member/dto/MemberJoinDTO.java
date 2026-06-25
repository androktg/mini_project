package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.member.domain.MemberVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinDTO {
    // ↓ 회원가입 요청 전용 필드. password가 반드시 필요하며, memberId·createdAt은 가입 시점에 DB가 채우므로 받지 않음.
    private String username;
    private String password;
    private String nickname;
    private String character;
    private String gender;
    private Integer age;
    private String region;

    public MemberVO toVO() {
        return MemberVO.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .character(character)
                .gender(gender)
                .age(age)
                .region(region)
                .build();
    }
}
