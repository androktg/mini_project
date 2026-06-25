package org.scoula.asset.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.asset.dto.AssetDTO;
import org.scoula.asset.mapper.AssetMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService{

    //의존성 주입 : 생성자 주입 방식으로 db 접근 객체(Mapper) 가져오는 역할
    private final AssetMapper mapper;

    @Override
    public List<AssetDTO> getList() {
        log.info("자산 목록 조회 요청 수신");
        // 추후 구현 단계에서 mapper.getList() 등으로 데이터를 조회한 뒤
        // VO 리스트를 DTO 리스트로 변환하여 반환하는 로직
        return List.of();
    }

    @Override
    public AssetDTO get(Long assetID) {
        log.info("자산 개별 상세 조회 - assetID: {}", assetID);
        return null;
    }

    @Override
    public void create(AssetDTO asset) {
        log.info("새로운 자산 등록 - asset: {}", asset);
        // mapper.insert(asset.toVO()) 형태의 비즈니스 로직이 구현될 자리
    }

    @Override
    public boolean update(AssetDTO asset) {
        log.info("자산 정보 수정 - asset: {}", asset);
        return false;
    }

    @Override
    public boolean delete(Long assetID) {
        log.info("자산 정보 삭제 - assetID: {}", assetID);
        return false;
    }




}

