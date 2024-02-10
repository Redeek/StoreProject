/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jee.projekt.controllers;

import java.io.IOException;
import java.security.Principal;
import javax.validation.Valid;
import jee.projekt.entities.Chocolate;
import jee.projekt.services.ChocolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ChocolateController {

    @Autowired
    private ChocolateService chocoService;

    @GetMapping("/chocolate/add")
    public String addChocolate(Model m){
        m.addAttribute("chocolate", new Chocolate());
        return "addChocolate";
    }

    @PostMapping("/chocolate/add")
    public String putChocolate( @Valid Chocolate chocolate, BindingResult bindingResult, @RequestParam(name="image",required = false) MultipartFile multipartFile)
            throws IOException {

        if (bindingResult.hasErrors()) {
            return "addChocolate";
        }

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            chocoService.addChocolate(chocolate);

        return "redirect:/chocolate/list";
    }

    @GetMapping("/chocolate/edit")
    public String editChocolate(Model m, @ModelAttribute(value = "chocolate") Chocolate chocolate) {
        m.addAttribute("chocolate", chocolate);

        return "chocolateEdit";
    }

    @PostMapping("/chocolate/edit")
    public String editChocolatePOST(@ModelAttribute(value = "chocolate") @Valid Chocolate chocolate, BindingResult bindingResult, Principal principal)
            throws IOException {
        if (bindingResult.hasErrors()) {
            return "chocolateEdit";
        }
        //Chocolate chocolateToUpdate = (Chocolate) chocoService.getChocolateById(chocolate.getId());

        chocoService.updateChocolate(chocolate);

        return "redirect:/chocolate/list";
    }

    @GetMapping("/chocolate/list")
    public String getChocolatelist(Model m){

        m.addAttribute("chocolateList",chocoService.getChocolateList());
        
        return "chocolateList";
    }

    @PostMapping("/chocolate/delete")
    public String deleteChocolate(@ModelAttribute("chocolate") Chocolate chocolate){
        
        System.out.println(chocolate.getId());

        chocoService.removeChocolate(chocolate.getId());
        return "redirect:/chocolate/list";
    }

}

