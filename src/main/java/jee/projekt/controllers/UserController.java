/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jee.projekt.controllers;

//import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import javax.validation.Valid;
import jee.projekt.entities.User;
import jee.projekt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passenc;

    @GetMapping("/login")
    public String loginG(){
        return "login";
    }

    @GetMapping("/rejestruj")
    public String rejestruj(Model m){
        m.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/rejestruj")
    public String registerPagePOST(@ModelAttribute(value = "user")  @Valid User user, BindingResult bindingResult)
            throws IOException {

        if (bindingResult.hasErrors()) {
            return "register";
        }

            String login = user.getLogin();
            if (Objects.nonNull(userService.getUserByLogin(login))) {
                String err = "Już istnieje użytkownik o takim loginie!";
                ObjectError error = new ObjectError("globalError", err);
                bindingResult.addError(error);
                return "register";
            }

            user.setPassword(passenc.encode(user.getPassword()));
            userService.addUser(user);


        return "redirect:/login";
    }

    @GetMapping("/user/delete")
    public String removeUser(Principal principal){
        User usr = userService.getUserByLogin(principal.getName());
        if(Objects.nonNull(usr)){
            userService.removeUser(usr.getId());
            return "redirect:/logout";
        }else {
            throw new IllegalArgumentException("Invalid user data!");
        }
    }

    @GetMapping("/user")
    public String showUser(Model m, Principal principal){
        m.addAttribute("user", userService.getUserByLogin(principal.getName()));
        return "userPage";
    }

    @GetMapping("/user/edit")
    public String editUserGET(Model m, Principal principal){
        m.addAttribute("user", userService.getUserByLogin(principal.getName()));
        return "editUserPage";
    }

    @PostMapping("/user/edit")
    //@Transactional
    public String editUserPOST(@Valid @ModelAttribute(value="user") User user,BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "editUserPage";
        }
        User usr = userService.getUserByLogin(principal.getName());
        if(Objects.nonNull(usr)){
            if(user.getId() != usr.getId()){
                System.out.println("RÓŻNE ID");
                bindingResult.rejectValue("login","","Podany login zarezerwowany!");
                return "editUserPage";
            }
            System.out.println("TUTAJ O UPDAETE" +user.getId());
            user.setPassword(passenc.encode(user.getPassword()));
            userService.updateUser(user);
            return "redirect:/user";
        }
        else throw new IllegalArgumentException("Invalid user data");

    }

}
