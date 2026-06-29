package org.scoula.asset.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.asset.dto.AssetDTO;
import org.scoula.asset.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
@Log4j2
public class AssetController {

    private final AssetService service;

    // 내 자산 목록 : GET /api/assets?memberId=1
    @GetMapping("")
    public ResponseEntity<List<AssetDTO>> getList(@RequestParam Long memberId) {
        return ResponseEntity.ok(service.getList(memberId));
    }

    // 자산 단건 : GET /api/assets/3
    @GetMapping("/{id}")
    public ResponseEntity<AssetDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    // 자산 등록 : POST /api/assets
    @PostMapping("")
    public ResponseEntity<AssetDTO> create(@RequestBody AssetDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // 자산 수정 : PUT /api/assets/3
    @PutMapping("/{id}")
    public ResponseEntity<AssetDTO> update(@PathVariable Long id,
                                           @RequestBody AssetDTO dto) {
        dto.setAssetId(id);
        return ResponseEntity.ok(service.update(dto));
    }

    // 자산 삭제 : DELETE /api/assets/3
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}