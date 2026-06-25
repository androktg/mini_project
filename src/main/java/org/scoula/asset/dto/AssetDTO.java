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


    // VO -> DTO 변환
    public static AssetDTO of(AssetVO vo){
    return AssetDTO.builder()
            .assetId(vo.getAssetId())
            .memberId(vo.getMemberId())
            .category(vo.getCategory())
            .assetType(vo.getAssetType())
            .amount(vo.getAmount())
            .createdAt(vo.getCreatedAt())
            .build();

    }
}