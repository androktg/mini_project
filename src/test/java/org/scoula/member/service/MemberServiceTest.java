package org.scoula.member.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Transactional          // 각 테스트 끝나면 자동 롤백 → 반복 실행해도 UNIQUE 충돌 없음
@Log4j2
class MemberServiceTest {

    @Autowired
    private MemberService service;

    // 테스트용 회원 하나 생성해서 반환 (생성된 memberId가 채워져 옴)
    private MemberDTO createSample() {
        MemberJoinDTO dto = new MemberJoinDTO();
        dto.setUsername("svc_user01");
        dto.setPassword("pw1234");
        dto.setNickname("서비스유저01");
        dto.setCharacter("char_a");
        dto.setGender("M");
        dto.setAge(28);
        dto.setRegion("부산");
        return service.join(dto);
    }

    @Test
    public void join() {
        MemberDTO result = createSample();
        log.info("가입 결과: " + result);   // password 없이 나오면 정상
    }

    @Test
    public void checkUsername() {
        createSample();   // 먼저 만들고
        log.info("svc_user01 중복? " + service.checkUsername("svc_user01")); // true 기대
        log.info("nobody999 중복? " + service.checkUsername("nobody999"));   // false 기대
    }

    @Test
    public void get() {
        MemberDTO created = createSample();                   // 먼저 만들고
        MemberDTO found = service.get(created.getUsername()); // username으로 조회
        log.info("조회 결과: " + found);
    }

    @Test
    public void getById() {
        MemberDTO created = createSample();                   // 먼저 만들고
        MemberDTO found = service.get(created.getMemberId()); // memberId로 조회
        log.info("id 조회 결과: " + found);
    }

    @Test
    public void update() {
        MemberDTO created = createSample();                   // 먼저 만들고

        created.setNickname("수정된닉네임");
        created.setCharacter("char_b");

        MemberDTO result = service.update(created);           // 그 id로 수정
        log.info("수정 결과: " + result);
    }

    @Test
    public void delete() {
        MemberDTO created = createSample();                   // 먼저 만들고
        boolean result = service.delete(created.getMemberId()); // 그 id로 삭제
        log.info("탈퇴 성공? " + result);
    }
}