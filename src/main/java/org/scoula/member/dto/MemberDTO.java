package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.member.domain.MemberVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long memberId;
    private String username;
    private String nickname;
    private String character;
    private String gender;
    private Integer age;
    private String region;
    private Date createdAt;
    // password 없음 — 조회 결과에 비밀번호를 노출하지 않으려고 의도적으로 제외

    // VO -> DTO (조회 결과 내보낼 때)
    public static MemberDTO of(MemberVO vo) {
        return MemberDTO.builder()
                .memberId(vo.getMemberId())
                .username(vo.getUsername())
                .nickname(vo.getNickname())
                .character(vo.getCharacter())
                .gender(vo.getGender())
                .age(vo.getAge())
                .region(vo.getRegion())
                .createdAt(vo.getCreatedAt())
                .build();
    }

    // DTO -> VO (수정하러 갈 때). password는 다루지 않음(수정은 닉네임·캐릭터만)
    public MemberVO toVO() {
        return MemberVO.builder()
                .memberId(memberId)
                .username(username)
                .nickname(nickname)
                .character(character)
                .gender(gender)
                .age(age)
                .region(region)
                .build();
    }
}