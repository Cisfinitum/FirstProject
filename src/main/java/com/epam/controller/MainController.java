package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;



@Controller
public class MainController {
  @GetMapping("/index")
  public String defaultPage(Principal user, ModelMap model) {
    if (user != null) {
      model.addAttribute("name", user.getName());
    } else {
      model.addAttribute("name", "guest");
    }
    return "homepage";
  }

  @GetMapping("/403")
  public ModelAndView accessDenied(Principal user) {
    ModelAndView model = new ModelAndView();

    if (user != null) {
      model.addObject("msg", "Hi " + user.getName()
              + ", you do not have permission to access this page!");
    } else {
      model.addObject("msg",
              "You do not have permission to access this page!");
    }

    model.setViewName("403");
    return model;
  }

  //// TODO only for testing user
  @GetMapping("/testuser")
  public String page(){
    return "redirect:/index";
  }

  //// TODO only for testing admin
  @GetMapping("/testadmin")
  public String page1(){
    return "testadmin";
  }

  @RequestMapping("/")
  public String anyPage(){
    return "redirect:/index";
  }
}
