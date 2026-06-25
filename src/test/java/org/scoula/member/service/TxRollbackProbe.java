package org.scoula.member.service;

import org.scoula.member.domain.MemberVO;
import org.scoula.member.mapper.MemberMapper;
import org.springframework.transaction.annotation.Transactional;

// 트랜잭션 롤백 검증용 프로브(테스트 전용).
// INSERT를 실행한 직후 일부러 RuntimeException을 던진다.
// 트랜잭션이 정상 동작하면 이 INSERT는 커밋되지 않고 롤백되어야 한다.
public class TxRollbackProbe {

    private final MemberMapper mapper;

    public TxRollbackProbe(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @Transactional
    public void insertThenFail(MemberVO vo) {
        mapper.insert(vo);                       // INSERT 실행
        throw new RuntimeException("의도적 실패 → 롤백되어야 함");
    }
}
