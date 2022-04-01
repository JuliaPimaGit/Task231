package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {
        List<User> list = userService.allUsers();
        model.addAttribute("list", list);
        return "all";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam (value = "id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam (value = "id") Long id) {
        userService.edit(user);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete")
    public String deleteUser ( @RequestParam (value = "id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }
}
