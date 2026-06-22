package org.scoula.asset.mapper;

import org.scoula.asset.domain.AssetVO;
import java.util.List;

public interface AssetMapper {

    // C - 자산 등록
    int insert(AssetVO asset);

    // R - 내 자산 목록 조회 (memberId로 본인 자산만)
    List<AssetVO> getList(Long memberId);

    // R - 자산 단건 조회 (assetId로)
    AssetVO get(Long assetId);

    // U - 자산 수정 (분류·자산명·금액)
    int update(AssetVO asset);

    // D - 자산 삭제
    int delete(Long assetId);
}