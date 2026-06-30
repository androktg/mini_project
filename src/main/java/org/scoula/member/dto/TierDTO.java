package org.scoula.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TierDTO {
    private Long memberId;
    private Long totalAsset;   // 총자산
    private String tier;       // Bronze / Silver / Gold / Diamond
}