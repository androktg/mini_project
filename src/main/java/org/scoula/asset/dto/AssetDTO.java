package org.scoula.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.asset.domain.AssetVO;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDTO{
    Long assetId;
    Long memberId;
    String category;
    String assetType;
    Long amount;
    Date createdAt;

    // VO -> DTO (조회 결과 내보낼 때) : static
    public static AssetDTO of(AssetVO vo) {
        return AssetDTO.builder()
                .assetId(vo.getAssetId())
                .memberId(vo.getMemberId())
                .category(vo.getCategory())
                .assetType(vo.getAssetType())
                .amount(vo.getAmount())
                .createdAt(vo.getCreatedAt())
                .build();
    }

    // DTO -> VO (저장/수정하러 갈 때) : 인스턴스 메서드
    public AssetVO toVO() {
        return AssetVO.builder()
                .assetId(assetId)
                .memberId(memberId)
                .category(category)
                .assetType(assetType)
                .amount(amount)
                .build();
    }
}