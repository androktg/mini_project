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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class MemberServiceTest {

    @Autowired
    private MemberService service;

    @Test
    public void join() {
        MemberJoinDTO dto = new MemberJoinDTO();
        dto.setUsername("svc_user01");
        dto.setPassword("pw1234");
        dto.setNickname("서비스유저01");
        dto.setCharacter("char_a");
        dto.setGender("M");
        dto.setAge(28);
        dto.setRegion("부산");

        MemberDTO result = service.join(dto);
        log.info("가입 결과: " + result);  // password 없이 나오면 정상
    }

    @Test
    public void get() {
        log.info(service.get("svc_user01"));
    }
}