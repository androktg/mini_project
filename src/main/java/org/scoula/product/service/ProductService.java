package org.scoula.product.service;

import org.scoula.product.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    // 상품 전체 목록 조회
    List<ProductDTO> getList();

    // 상품 단건 조회
    ProductDTO get(Long productId);
}