package org.scoula.product.mapper;

import org.scoula.product.domain.ProductVO;
import java.util.List;

public interface ProductMapper {

    public List<ProductVO> getList();

    public ProductVO get(Long productId);

    //추천 상품 1개(이율 최고로)
    ProductVO getRecommend(long amount);
}