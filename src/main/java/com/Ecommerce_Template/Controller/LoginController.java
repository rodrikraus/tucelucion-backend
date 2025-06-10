package com.Ecommerce_Template.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Ecommerce_Template.Models.User;
import com.Ecommerce_Template.Repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // User exists and password matches, now check role
            if ("admin".equals(user.getRole())) {
                session.setAttribute("loggedInUser", username);
                session.setAttribute("userRole", user.getRole());
                return "redirect:/productos";
            } else {
                // Role is not admin or null
                model.addAttribute("error", "Access denied: User is not an administrator.");
                return "login";
            }
        } else {
            // User not found or password incorrect
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
