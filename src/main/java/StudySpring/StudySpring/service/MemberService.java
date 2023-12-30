package StudySpring.StudySpring.service;

import StudySpring.StudySpring.domain.Member;
import StudySpring.StudySpring.repository.MemberRepository;
import StudySpring.StudySpring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberService { // cmd shift t 를 같이 누르면 test class 생성 단축키
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); 테스트에서 서로 같은 레포지토리를 사용해야 하기 때문에 생성자부분 삭제
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { // 멤버레포지토리를 외부에서 넣어줄 수 있도록 constructor생성
        this.memberRepository = memberRepository;
    }

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
