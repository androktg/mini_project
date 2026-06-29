package org.scoula.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.product.dto.ProductDTO;
import org.scoula.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                       // JSON으로 응답하는 컨트롤러
@RequestMapping("/api/products")      // 이 컨트롤러의 공통 URL 앞부분
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService service;   // 서비스 주입

    // 상품 목록 : GET /api/products
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getList() {
        return ResponseEntity.ok(service.getList());
    }

    // 상품 단건 : GET /api/products/3
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }
}