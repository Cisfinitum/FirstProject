package com.epam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

  @RequestMapping(method = RequestMethod.GET)
  public String hello() {
    return "index";
  }

  @RequestMapping(value = "/hello",method = RequestMethod.GET)
  public String hello2() {
    return "index2";
  }
}
