package org.scoula.product.mapper;

import org.scoula.product.domain.ProductVO;
import java.util.List;

public interface ProductMapper {

    public List<ProductVO> getList();

    public ProductVO get(Long productId);

    //추천 상품
    List<ProductVO> getRecommendList(Long maxAmount);
}