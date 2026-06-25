package org.scoula.asset.service;

import lombok.RequiredArgsConstructor;
import org.scoula.asset.domain.AssetVO;
import org.scoula.asset.dto.AssetDTO;
import org.scoula.asset.mapper.AssetMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetMapper mapper;

    @Override
    public List<AssetDTO> getList(Long memberId) {
        // 본인(memberId) 자산만 조회 -> 각 VO를 DTO로 변환
        return mapper.getList(memberId).stream()
                .map(AssetDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public AssetDTO get(Long assetId) {
        // id로 조회, 없으면 예외, 내 id로 들어가서 각각 유동자산, 비유동자산 조회, 만약에 작성된게 없으면 예외 처리!
        AssetVO vo = Optional.ofNullable(mapper.get(assetId))
                .orElseThrow(NoSuchElementException::new);
        return AssetDTO.of(vo);
    }

    @Transactional
    @Override
    public AssetDTO create(AssetDTO dto) {
        // 1. DTO -> VO 변환
        AssetVO vo = dto.toVO();
        // 2. DB 저장 (insert 후 useGeneratedKeys로 assetId가 vo에 채워짐)
        mapper.insert(vo);
        // 3. 저장된 자산을 다시 조회해서 반환
        return get(vo.getAssetId());
    }

    @Transactional
    @Override
    public AssetDTO update(AssetDTO dto) {
        // 1. 수정 대상이 존재하는지 확인 (없으면 예외)
        Optional.ofNullable(mapper.get(dto.getAssetId()))
                .orElseThrow(NoSuchElementException::new);
        // 2. DTO -> VO 변환 후 수정
        mapper.update(dto.toVO());
        // 3. 수정된 결과를 다시 조회해서 반환
        return get(dto.getAssetId());
    }

    @Transactional
    @Override
    public void delete(Long assetId) {
        // 존재 확인 후 삭제
        Optional.ofNullable(mapper.get(assetId))
                .orElseThrow(NoSuchElementException::new);
        mapper.delete(assetId);
    }
}