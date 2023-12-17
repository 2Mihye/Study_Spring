package StudySpring.StudySpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {
    @GetMapping("study")
    public String Study(Model model){
        model.addAttribute("data", "hello~");
        return "study";  
    }
}
