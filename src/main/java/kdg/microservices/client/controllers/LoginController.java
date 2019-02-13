package kdg.microservices.client.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kdg.microservices.client.model.User;
import kdg.microservices.client.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

@Controller
public class LoginController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/login")
    public String goToLoginForm(Model model) {
        model.addAttribute("userobject", new User());
        return "login";
    }

    @PostMapping("/login")
    public String goToLoginFom(User user, HttpServletResponse response) {
        try {
            Cookie cookie = studentService.postResult();
            response.addCookie(cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
