package org.scoula.member.service;

import lombok.RequiredArgsConstructor;
import org.scoula.member.domain.MemberVO;
import org.scoula.member.dto.MemberDTO;
import org.scoula.member.dto.MemberJoinDTO;
import org.scoula.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // DB 접근 담당(MyBatis Mapper). 스프링이 생성자를 통해 자동으로 넣어줌.
    final private MemberMapper mapper;

    @Override
    public MemberDTO join(MemberJoinDTO member) {
        // 1. 아이디 중복 선검사 → 중복이면 예외를 던져서 가입을 중단시킴
        if (checkUsername(member.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다: " + member.getUsername());
        }
        // 2. 바깥에서 받은 DTO를 DB용 VO로 변환한 뒤 INSERT 실행
        //    (Mapper의 insert는 useGeneratedKeys 설정이라, 저장 후 vo의 memberId에 새 번호가 채워짐)
        MemberVO vo = member.toVO();
        mapper.insert(vo);
        // 3. 저장된 결과를 다시 DTO로 변환해 반환 (of()라서 password는 빠진 안전한 형태로 나감)
        return MemberDTO.of(vo);
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

    @Override
    public MemberDTO update(MemberDTO member) {
        // 1. 수정 대상이 실제로 존재하는지 먼저 확인. 없으면 예외.
        Optional.ofNullable(mapper.getById(member.getMemberId()))
                .orElseThrow(NoSuchElementException::new);
        // 2. DTO -> VO로 바꿔서 UPDATE 실행
        mapper.update(member.toVO());
        // 3. 방금 수정된 최신 정보를 다시 읽어서 반환 (호출한 쪽이 최신 상태를 받도록)
        return get(member.getMemberId());
    }

    @Override
    public boolean delete(Long memberId) {
        return mapper.delete(memberId) > 0;
    }

    @Override
    public boolean checkUsername(String username) {
        return mapper.checkUsername(username) > 0;
    }
}
