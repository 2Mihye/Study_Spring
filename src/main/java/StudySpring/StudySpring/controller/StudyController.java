package StudySpring.StudySpring.controller;

import org.apache.tomcat.util.net.TLSClientHelloExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudyController {
    @GetMapping("study")
    public String Study(Model model){
        model.addAttribute("data", "hello~");
        return "study";
    }

    @GetMapping("study-mvc")
    public String studyMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "study-template";
    }

    @GetMapping("study-string")
    @ResponseBody // http에서  body 부분에다 직접 넣어주겠다는 의미.
    public String studyString(@RequestParam("name") String name){
        return "hello " + name; // ResponseBody 때문에 웹에서 소스코드 보기를 해도 현재 코드를 그대로 내려줌.
    }

    @GetMapping("study-Api")
    @ResponseBody
    public Study studyApi(@RequestParam("name") String name) {
        Study study = new Study();
        study.setName(name);
        return study;
    }

    static class Study {
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @GetMapping("study-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("study-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
