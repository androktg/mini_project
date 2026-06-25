package org.scoula.member.service;

import org.scoula.member.dto.MemberDTO;

public interface MemberService {

    // 회원가입 (아이디 중복 검사 후 등록)
    MemberDTO join(MemberDTO member);

    // username으로 회원 조회 (없으면 예외)
    MemberDTO get(String username);

    // memberId로 회원 조회 (없으면 예외)
    // ↑ 위 get(String)과 이름은 같지만 파라미터 타입이 달라(메서드 오버로딩). 자바가 타입으로 구분해줌.
    MemberDTO get(Long memberId);

    // 회원 정보 수정 (닉네임·캐릭터)
    MemberDTO update(MemberDTO member);

    // 회원 탈퇴
    boolean delete(Long memberId);

    // 아이디 중복확인 (중복이면 true)
    boolean checkUsername(String username);
}
