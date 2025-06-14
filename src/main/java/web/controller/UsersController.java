package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

 @GetMapping("/")
    public String index(Model model) {
       List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/updateUser")
    public String updateUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "updateUser";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") long id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
