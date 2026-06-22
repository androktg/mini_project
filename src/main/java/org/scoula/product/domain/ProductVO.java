package org.scoula.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVO {
    Long productId;
    String productName;
    String bank;
    String productType;
    Double baseRate;
    Double maxRate;
    Integer periodMonth;
    Long minAmount;
    Long maxAmount;
    Integer minPeriod;
    Integer maxPeriod;
    String feature;
    String description;
}