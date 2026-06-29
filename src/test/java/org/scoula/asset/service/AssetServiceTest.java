package org.scoula.asset.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.asset.dto.AssetDTO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class AssetServiceTest {

    @Autowired
    private AssetService service;

    @Test
    public void create() {
        AssetDTO dto = AssetDTO.builder()
                .memberId(1L)          // ⚠️ 실제 존재하는 member_id여야 함 (FK)
                .category("유동")
                .assetType("예금")
                .amount(5000000L)
                .build();
        AssetDTO result = service.create(dto);
        log.info("등록 결과: " + result);
    }

    @Test
    public void getList() {
        List<AssetDTO> list = service.getList(1L);
        list.forEach(a -> log.info(a));
    }

    @Test
    public void get() {
        // ⚠️ 실제 존재하는 assetId로 바꿔서 테스트 (getList로 확인한 id)
        AssetDTO asset = service.get(1L);
        log.info("단건 조회: " + asset);
    }

    @Test
    public void update() {
        // ⚠️ 실제 존재하는 assetId 필요
        AssetDTO dto = AssetDTO.builder()
                .assetId(1L)           // 수정 대상 id
                .memberId(1L)
                .category("투자")
                .assetType("주식")
                .amount(8000000L)
                .build();
        AssetDTO result = service.update(dto);
        log.info("수정 결과: " + result);
    }

    @Test
    public void delete() {
        // ⚠️ 실제 존재하는 assetId 필요 (지우면 사라지니 주의)
        service.delete(1L);
        log.info("삭제 완료");
    }
}