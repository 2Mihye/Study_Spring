package StudySpring.StudySpring.repository;

import StudySpring.StudySpring.domain.Member;
// import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import StudySpring.StudySpring.repository.MemoryMemberRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 끝날 때마다 레포지토리를 깔끔하게 지워주는 메서드를 만들어야 함.
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("Mihye");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional일 때 get으로 바로 꺼낼 수 있는데 좋은 방법은 아님.
        // Assertions.assertEquals(member, result); // 두 값이 같은지 확인할 때 사용.
        assertThat(member).isEqualTo(result); // 위와 같은 기능으로 검증할 수 있음.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
