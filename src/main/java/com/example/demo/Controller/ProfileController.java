package com.example.demo.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
public class ProfileController {
    @RequestMapping("/profile")
    public String showProfile(){
        return  "profile.html";
        }
}
