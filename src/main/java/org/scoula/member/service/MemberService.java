package org.scoula.member.service;

import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;

public interface MemberService {

    // 아이디 중복확인 - 이미 있으면 true
    boolean checkUsername(String username);

    // 회원가입
    MemberDTO join(MemberJoinDTO member);

    // 조회 - username으로 (로그인/조회용)
    MemberDTO get(String username);

    // 조회 - memberId로 (수정·탈퇴 시 존재 확인 등)
    MemberDTO get(Long memberId);

    // 회원정보 수정 (닉네임·캐릭터)
    MemberDTO update(MemberDTO member);

    // 회원 탈퇴 - 성공하면 true
    boolean delete(Long memberId);
}