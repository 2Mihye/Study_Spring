package StudySpring.StudySpring.service;

import StudySpring.StudySpring.domain.Member;
import StudySpring.StudySpring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest { // 테스트는 클래스명을 과감하게 한글로 바꿔도 상관 없음

    MemberService memberService; // service 뿐이라 클리어가 안 됨.
    MemoryMemberRepository memberRepository;
    // MemberService에서 만든 레포지토리와 text 클래스에서 만든 레포지토리가 서로 다른 레포지토리, 다른 인스턴스임. 같은 것으로 테스트 하는 것이 맞기 떄문에 Service에서 바꿔야 함.

    @BeforeEach // 같은 레포지토리멤버 사용
    public void beforeEach() { // 디펜던시 인젝션(DI) // Service입장에선 멤버레포지토리를 외부에서 넣어주는 것
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given 이러한 상황이 주어져서
        Member member = new Member();
        member.setName("Mihye");

        // when 이것을 실행했을 떄
        Long saveId = memberService.join(member);

        // then 결과가 이렇게 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // cmd option v : 단축키는...
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        // then
    }

    @Test
    void findOne() {
    }
}