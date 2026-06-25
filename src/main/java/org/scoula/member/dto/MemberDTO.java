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
    // ↓ 화면/API에서 다루는 회원 정보 필드들. DB 컬럼(snake_case)과 이름은 같지만 자바는 camelCase로 씀.
    private Long memberId;
    private String username;
    private String password;   // 입력(가입)용. 출력 시에는 of()에서 일부러 담지 않아 외부로 노출되지 않음
    private String nickname;
    private String character;
    private String gender;
    private Integer age;
    private String region;
    private Date createdAt;

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

    public MemberVO toVO() {
        return MemberVO.builder()
                .memberId(memberId)
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
