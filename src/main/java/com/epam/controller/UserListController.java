package com.epam.controller;

import com.epam.model.Person;
import com.epam.service.PersonDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class UserListController {

    private final PersonDetailsServiceImpl personDetailsServiceImpl;

    @Autowired
    UserListController(PersonDetailsServiceImpl personDetailsServiceImpl) {
        this.personDetailsServiceImpl = personDetailsServiceImpl;
    }
    @GetMapping
    public String clientsPage() {
        return "redirect:/clients/1";
    }

    @GetMapping("/{id}")
    public String listOfUsers(@PathVariable Integer id, ModelMap modelMap) {
        List<Person> clientsList = personDetailsServiceImpl.listOfUsers(id, 4);
        int generalAmount = personDetailsServiceImpl.amountOfUsers();
        int amount = 4;
        modelMap.addAttribute("listOfUsers", clientsList);
        modelMap.addAttribute("generalAmount", generalAmount);
        modelMap.addAttribute("amount", (generalAmount % amount == 0) ? generalAmount / amount : generalAmount / amount + 1);
        return "/clients";
    }

    @GetMapping("/addToBlackList/{id}")
    public String addToBlackList(@PathVariable Integer id) {
        personDetailsServiceImpl.addToBlackList(id);
        return "redirect:/clients";
    }

    @GetMapping("/removeFromBlackList/{id}")
    public String removeFromBlackList(@PathVariable Integer id) {
        personDetailsServiceImpl.removeFromBlackList(id);
        return "redirect:/clients";
    }
}
