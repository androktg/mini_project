package org.scoula.asset.service;

import org.scoula.asset.dto.AssetDTO;

import java.util.List;


public interface AssetService {
    // 전체 asset 조회
    public List<AssetDTO> getList();

    // asset 개별조회
    public AssetDTO get(Long assetID);

    // 생성(사용자가 자산 정보 조회, 수정, 삭제 하려면 자산정보가 데이터베이스에 먼저 생성되어야 하기 때문에)
    public void create(AssetDTO asset);


    // 수정(수정할 전체 데이터를 담은 DTO를 받아와야 해서, asset)
    public boolean update(AssetDTO asset);

    // 삭제
    public boolean delete(Long assetID);
}