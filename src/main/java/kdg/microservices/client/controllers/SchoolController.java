package kdg.microservices.client.controllers;

import kdg.microservices.client.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/schools")
    public String goToIndex(Model model) {
        try {
            model.addAttribute("schools", schoolService.getSchools());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "school";
    }
}
