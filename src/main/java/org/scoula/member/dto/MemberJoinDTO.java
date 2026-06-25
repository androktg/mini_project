package org.scoula.member.dto;

import lombok.Data;
import org.scoula.member.domain.MemberVO;

@Data
public class MemberJoinDTO {
    private String username;
    private String password;   // 가입 땐 비밀번호를 받아야 함
    private String nickname;
    private String character;
    private String gender;
    private Integer age;
    private String region;

    // DTO -> VO (가입 저장하러 갈 때)
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