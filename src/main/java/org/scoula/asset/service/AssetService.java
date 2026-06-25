package org.scoula.asset.service;

import org.scoula.asset.dto.AssetDTO;
import java.util.List;

public interface AssetService {

    // 내 자산 목록 조회
    List<AssetDTO> getList(Long memberId);

    // 자산 단건 조회
    AssetDTO get(Long assetId);

    // 자산 등록
    AssetDTO create(AssetDTO dto);

    // 자산 수정
    AssetDTO update(AssetDTO dto);

    // 자산 삭제
    void delete(Long assetId);
}