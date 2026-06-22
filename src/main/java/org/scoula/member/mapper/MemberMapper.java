package org.scoula.member.mapper;

import org.scoula.member.domain.MemberVO;

public interface MemberMapper {

    // C - 회원 등록 (회원가입)
    int insert(MemberVO member);

    // R - 회원 정보 확인 (username으로 조회 - 로그인/조회 시 사용)
    MemberVO get(String username);

    // R - 회원 정보 확인 (memberId로 조회 - 수정·탈퇴 시 존재 확인 등)
    MemberVO getById(Long memberId);

    // U - 회원 정보 수정 (닉네임·캐릭터)
    int update(MemberVO member);

    // D - 회원 탈퇴
    int delete(Long memberId);

    // (지원) 아이디 중복확인 - 있으면 1 이상, 없으면 0
    int checkUsername(String username);
}