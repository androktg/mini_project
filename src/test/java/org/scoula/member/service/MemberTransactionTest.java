package org.scoula.member.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.member.domain.MemberVO;
import org.scoula.member.mapper.MemberMapper;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, MemberTransactionTest.TxConfig.class})
@Log4j2
class MemberTransactionTest {

    @Autowired
    private MemberService service;

    @Autowired
    private MemberMapper mapper;

    @Autowired
    private TxRollbackProbe probe;

    // 1) @EnableTransactionManagement + @Transactional 이 실제로 적용됐는지 확인
    //    트랜잭션이 활성화되면 서비스 빈은 AOP 프록시로 감싸진다.
    @Test
    public void 트랜잭션_프록시가_적용된다() {
        boolean isProxy = AopUtils.isAopProxy(service);
        log.info("MemberService 가 트랜잭션 프록시인가? " + isProxy);
        assertTrue(isProxy, "트랜잭션이 적용됐다면 service 는 AOP 프록시여야 한다");
    }

    // 2) 실제 롤백 검증: INSERT 직후 예외 → DB에 데이터가 남으면 안 된다.
    //    (트랜잭션이 꺼져 있었다면 autocommit 으로 커밋되어 count 가 1이 되고 테스트 실패)
    @Test
    public void 예외가_나면_INSERT가_롤백된다() {
        String uniqueName = "tx_test_" + System.currentTimeMillis();
        MemberVO vo = MemberVO.builder()
                .username(uniqueName)
                .password("pw1234")
                .nickname("닉_" + System.currentTimeMillis())
                .character("char_a")
                .gender("M")
                .age(30)
                .region("서울")
                .build();

        // INSERT 후 강제 예외 → 트랜잭션이면 롤백되어야 함
        assertThrows(RuntimeException.class, () -> probe.insertThenFail(vo));

        // 롤백됐다면 해당 username 은 DB에 존재하지 않아야 한다(count == 0)
        int count = mapper.checkUsername(uniqueName);
        log.info("롤백 후 '" + uniqueName + "' 존재 개수 = " + count + " (0이면 롤백 성공)");
        assertEquals(0, count, "예외 발생 시 INSERT 가 롤백되어 DB에 남으면 안 된다");
    }

    // 프로브를 이 테스트 컨텍스트에만 빈으로 등록(운영 코드/다른 테스트에 영향 없음)
    @Configuration
    static class TxConfig {
        @Bean
        public TxRollbackProbe txRollbackProbe(MemberMapper mapper) {
            return new TxRollbackProbe(mapper);
        }
    }
}
