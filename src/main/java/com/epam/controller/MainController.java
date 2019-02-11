package com.epam.controller;

import com.epam.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
public class MainController {

  private final PersonsService personsService;

 @Autowired
 public MainController(PersonsService personsService){
   this.personsService = personsService;
 }

  @GetMapping("/index")
  public String defaultPage(Principal user, ModelMap model) {
    personsService.printPersonsNames();

    if (user != null) {
      model.addAttribute("name", user.getName());
    } else {
      model.addAttribute("name", "guest");
    }

    return "homepage";
  }

  @GetMapping("/403")
  public ModelAndView accessDeniedPage(Principal user) {
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

  //// TODO only for testing user and admin
  @GetMapping("/testuser")
  public String userPage(){
    return "redirect:/index";
  }

  @GetMapping("/testadmin")
  public String adminPage(){
    return "testadmin";
  }

  @GetMapping("/")
  public String anyPage(){
    return "redirect:/index";
  }
}
