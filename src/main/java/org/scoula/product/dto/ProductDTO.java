package org.scoula.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.product.domain.ProductVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long productId;
    private String productName;
    private String bank;
    private String productType;
    private Double baseRate;
    private Double maxRate;
    private Integer periodMonth;
    private Long minAmount;
    private Long maxAmount;
    private Integer minPeriod;
    private Integer maxPeriod;
    private String feature;
    private String description;

    // VO -> DTO (조회 결과 내보낼 때) : static
    public static ProductDTO of(ProductVO vo) {
        return ProductDTO.builder()
                .productId(vo.getProductId())
                .productName(vo.getProductName())
                .bank(vo.getBank())
                .productType(vo.getProductType())
                .baseRate(vo.getBaseRate())
                .maxRate(vo.getMaxRate())
                .periodMonth(vo.getPeriodMonth())
                .minAmount(vo.getMinAmount())
                .maxAmount(vo.getMaxAmount())
                .minPeriod(vo.getMinPeriod())
                .maxPeriod(vo.getMaxPeriod())
                .feature(vo.getFeature())
                .description(vo.getDescription())
                .build();
    }
}