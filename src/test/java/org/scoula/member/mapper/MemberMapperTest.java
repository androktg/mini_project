package org.scoula.member.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.member.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class MemberMapperTest {

    @Autowired
    private MemberMapper mapper;

    @Test
    public void insert() {
        MemberVO member = MemberVO.builder()
                .username("test01")
                .password("pw1234")
                .nickname("테스터")
                .character("char_a")
                .gender("M")
                .age(25)
                .region("서울")
                .build();

        int result = mapper.insert(member);
        log.info("insert 결과(영향 행 수): " + result);
        log.info("생성된 memberId: " + member.getMemberId()); // useGeneratedKeys로 채워짐
    }

    @Test
    public void get() {
        MemberVO member = mapper.get("test01");
        log.info(member);
    }

    @Test
    public void checkUsername() {
        int count = mapper.checkUsername("test01");
        log.info("중복 수(0이면 사용가능): " + count);
    }
}