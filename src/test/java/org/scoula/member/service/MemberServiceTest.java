package org.scoula.member.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class MemberServiceTest {

    // 스프링이 만들어 둔 MemberService Bean을 자동으로 넣어줌(@Autowired = 의존성 주입)
    @Autowired
    private MemberService service;

    @Test
    public void join() {
        // 빌더 패턴으로 가입에 쓸 회원 정보를 만든다
        MemberDTO member = MemberDTO.builder()
                .username("svc_test01")
                .password("pw1234")
                .nickname("서비스테스터")
                .character("char_a")
                .gender("M")
                .age(25)
                .region("서울")
                .build();

        MemberDTO joined = service.join(member);
        log.info("가입 결과(password 제외): " + joined);
    }

    @Test
    public void get() {
        log.info(service.get("svc_test01"));
    }

    @Test
    public void checkUsername() {
        log.info("중복 여부(true면 사용 불가): " + service.checkUsername("svc_test01"));
    }
}
