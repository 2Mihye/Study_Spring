package StudySpring.StudySpring.controller;

import StudySpring.StudySpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 해당 어노테이션을 해두면 멤버 컨트롤러 객체를 생성해서 컨테이너에 넣어두고 스프링 빈이 관리된다.
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
