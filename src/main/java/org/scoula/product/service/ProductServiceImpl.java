package org.scoula.product.service;

import lombok.RequiredArgsConstructor;
import org.scoula.asset.mapper.AssetMapper;
import org.scoula.product.domain.ProductVO;
import org.scoula.product.dto.ProductDTO;
import org.scoula.product.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductMapper mapper;
    final private AssetMapper assetMapper; // 추가(자산 조회용)

    @Override
    public List<ProductDTO> getList() {
        // 1. mapper로 전체 조회 (VO 리스트)
        // 2. 각 VO를 DTO로 변환해서 리스트로 모으기
        return mapper.getList().stream()
                .map(ProductDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO get(Long productId) {
        // 1. id로 조회, 없으면 예외
        ProductVO vo = Optional.ofNullable(mapper.get(productId))
                .orElseThrow(NoSuchElementException::new);
        // 2. VO -> DTO 변환
        return ProductDTO.of(vo);
    }

    @Override
    public ProductDTO recommend(Long memberId){
        // 고객 총 자산 합계
        long amount = assetMapper.getTotalAmount(memberId);

        // 가입 가능한 상품 중 이율 최고 1개
        ProductVO vo = Optional.ofNullable(mapper.getRecommend(amount))
                .orElseThrow(NoSuchElementException::new);
        return ProductDTO.of(vo);
    }
}