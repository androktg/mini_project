package org.scoula.asset.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.asset.dto.AssetDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Transactional          // 각 테스트 끝나면 자동 롤백 → DB 안 더럽힘 + 반복 실행 OK
@Log4j2
class AssetServiceTest {

    @Autowired
    private AssetService service;

    // ⚠️ member 테이블에 실제 존재하는 회원 id (지금 DB 기준 test01 = 1)
    private static final Long MEMBER_ID = 1L;

    // 테스트용 자산 하나 생성해서 반환 (생성된 assetId가 채워져 옴)
    private AssetDTO createSample() {
        AssetDTO dto = AssetDTO.builder()
                .memberId(MEMBER_ID)
                .category("유동")
                .assetType("예금")
                .amount(5000000L)
                .build();
        return service.create(dto);
    }

    @Test
    public void create() {
        AssetDTO result = createSample();
        log.info("등록 결과: " + result);
    }

    @Test
    public void getList() {
        createSample();   // 최소 1건 보장
        List<AssetDTO> list = service.getList(MEMBER_ID);
        list.forEach(a -> log.info(a));
    }

    @Test
    public void get() {
        AssetDTO created = createSample();                  // 먼저 만들고
        AssetDTO found = service.get(created.getAssetId()); // 그 id로 조회
        log.info("단건 조회: " + found);
    }

    @Test
    public void update() {
        AssetDTO created = createSample();                  // 먼저 만들고

        created.setCategory("투자");
        created.setAssetType("주식");
        created.setAmount(8000000L);

        AssetDTO result = service.update(created);          // 그 id로 수정
        log.info("수정 결과: " + result);
    }

    @Test
    public void delete() {
        AssetDTO created = createSample();                  // 먼저 만들고
        service.delete(created.getAssetId());               // 그 id로 삭제
        log.info("삭제 완료: assetId=" + created.getAssetId());
    }
}