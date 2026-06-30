package org.scoula.member.service;

import lombok.RequiredArgsConstructor;
import org.scoula.asset.mapper.AssetMapper;
import org.scoula.member.domain.MemberVO;
import org.scoula.member.domain.Tier;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.dto.TierDTO;
import org.scoula.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // DB 접근 담당(MyBatis Mapper). 스프링이 생성자로 자동 주입.
    private final MemberMapper mapper;

    private final AssetMapper assetMapper;

    @Override
    public boolean checkUsername(String username) {
        // 중복 카운트가 0보다 크면 이미 존재 -> true
        return mapper.checkUsername(username) > 0;
    }

    @Transactional
    @Override
    public MemberDTO join(MemberJoinDTO member) {
        // 1. 아이디 중복 선검사 -> 중복이면 예외로 가입 중단
        if (checkUsername(member.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다: " + member.getUsername());
        }
        // 2. DTO -> VO 변환 후 INSERT (useGeneratedKeys로 저장 후 vo.memberId 채워짐)
        MemberVO vo = member.toVO();

        // 3. (추후) 비밀번호 암호화 자리 - Security 단계에서 추가
        //    vo.setPassword(passwordEncoder.encode(vo.getPassword()));

        mapper.insert(vo);

        // 4. 저장된 회원을 다시 조회해서 반환 (createdAt까지 채워진 정확한 상태)
        return get(vo.getMemberId());
    }

    @Override
    public MemberDTO get(String username) {
        MemberVO vo = Optional.ofNullable(mapper.get(username))
                .orElseThrow(NoSuchElementException::new);
        return MemberDTO.of(vo);
    }

    @Override
    public MemberDTO get(Long memberId) {
        MemberVO vo = Optional.ofNullable(mapper.getById(memberId))
                .orElseThrow(NoSuchElementException::new);
        return MemberDTO.of(vo);
    }

    @Transactional
    @Override
    public MemberDTO update(MemberDTO member) {
        // 1. 수정 대상 존재 확인 (없으면 예외)
        Optional.ofNullable(mapper.getById(member.getMemberId()))
                .orElseThrow(NoSuchElementException::new);
        // 2. DTO -> VO 변환 후 UPDATE
        mapper.update(member.toVO());
        // 3. 수정된 최신 정보 다시 조회해 반환
        return get(member.getMemberId());
    }

    @Transactional
    @Override
    public boolean delete(Long memberId) {
        return mapper.delete(memberId) > 0;
    }

    @Override
    public TierDTO getTier(Long memberId) {
        Long total = assetMapper.getTotalAmount(memberId);   // 총자산
        Tier tier = Tier.of(total);                          // 티어 판정
        return new TierDTO(memberId, total, tier.getName());
    }
}