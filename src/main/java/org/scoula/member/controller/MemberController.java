package org.scoula.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService service;

    // 아이디 중복확인 : GET /api/members/check?username=kim123
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok(service.checkUsername(username));
    }

    // 회원가입 : POST /api/members
    @PostMapping("")
    public ResponseEntity<MemberDTO> join(@RequestBody MemberJoinDTO dto) {
        return ResponseEntity.ok(service.join(dto));
    }

    // 회원 조회(username) : GET /api/members/kim123
    @GetMapping("/{username}")
    public ResponseEntity<MemberDTO> get(@PathVariable String username) {
        return ResponseEntity.ok(service.get(username));
    }

    // 회원정보 수정 : PUT /api/members/3
    @PutMapping("/{memberId}")
    public ResponseEntity<MemberDTO> update(@PathVariable Long memberId,
                                            @RequestBody MemberDTO dto) {
        dto.setMemberId(memberId);
        return ResponseEntity.ok(service.update(dto));
    }

    // 회원 탈퇴 : DELETE /api/members/3
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> delete(@PathVariable Long memberId) {
        service.delete(memberId);
        return ResponseEntity.ok().build();
    }
}