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
    Long assetId; // pk,1부터 자동 올라가는
    Long memberId; // 아이디
    String category; // 유동자산이냐 비유동자산이냐
    String assetType; //
    Long amount; // 각 자산의 금액
    Date createdAt;
}