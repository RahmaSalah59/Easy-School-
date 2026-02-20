package com.example.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String showDashboard (Model model, Authentication authentication){

        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles",authentication.getAuthorities().toString());
        if(authentication.getAuthorities().toString().equals("[ROLE_ADMIN]"))
            model.addAttribute("check",true);
        else
            model.addAttribute("check" , false);

        return  "dashboard.html";
    }

}
