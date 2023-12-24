package StudySpring.StudySpring.service;

import StudySpring.StudySpring.domain.Member;
import StudySpring.StudySpring.repository.MemberRepository;
import StudySpring.StudySpring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
        회원가입
    */
    public long join(Member member){
        // 같은 이름을 가진 중복회원은 안된다는 규칙이 있을 때
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member); // 통과하면 저장
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
        전체 회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*
        회원 한 명 조회
    */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
