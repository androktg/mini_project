package org.scoula.asset.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.asset.domain.AssetVO;
import org.scoula.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class AssetMapperTest {

    @Autowired
    private AssetMapper mapper;

    @Test
    public void insert() {
        AssetVO asset = AssetVO.builder()
                .memberId(1L)          //실제 존재하는 member_id여야 함 (FK)
                .category("유동")
                .assetType("예금")
                .amount(5000000L)
                .build();

        int result = mapper.insert(asset);
        log.info("insert 결과: " + result);
        log.info("생성된 assetId: " + asset.getAssetId());
    }

    @Test
    public void getList() {
        List<AssetVO> list = mapper.getList(1L);  // 1번 회원의 자산 목록
        for (AssetVO asset : list) {
            log.info(asset);
        }
    }
}