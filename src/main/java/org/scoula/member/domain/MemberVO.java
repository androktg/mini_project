package org.scoula.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO {
    Long memberId;
    String username;
    String password;
    String nickname;
    String character;
    String gender;
    Integer age;
    String region;
    Date createdAt;
}