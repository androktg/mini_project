package org.scoula.asset.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetVO {
    Long assetId;
    Long memberId;
    String category;
    String assetType;
    Long amount;
    Date createdAt;
}