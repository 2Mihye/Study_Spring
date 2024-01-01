package StudySpring.StudySpring;

import StudySpring.StudySpring.repository.MemberRepository;
import StudySpring.StudySpring.repository.MemoryMemberRepository;
import StudySpring.StudySpring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
