package kdg.microservices.client.controllers;

import kdg.microservices.client.model.Student;
import kdg.microservices.client.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Controller
public class HomeController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String goToIndex(Model model) {
        try {
            model.addAttribute("students", studentService.getStudents());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String goToEditPage(@PathVariable("id") int id, Model model, @CookieValue(value = "access_token",defaultValue = "noCookie") String accessToken){
        Student student = null;
        try {
            student = studentService.getStudent(id,accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("student", student);
        return "edit";

    }

    @GetMapping("/hello")
    public String helloConference() {
        try {
            return studentService.getHello();
        } catch (IOException e) {
            e.printStackTrace();
            return "getHello() mislukt!";
        }
    }
}
