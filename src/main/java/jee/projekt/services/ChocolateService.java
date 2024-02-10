/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jee.projekt.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import jee.projekt.entities.Chocolate;
import jee.projekt.repository.ChocolateRepository;

@Service
public class ChocolateService {

    @Autowired
    private ChocolateRepository chocolateRepo;

    public List<Chocolate> getChocolateList(){
        return (List<Chocolate>) chocolateRepo.findAll();
    }

    public void addChocolate(@Valid Chocolate choco){
        
        chocolateRepo.save(choco);
    }

    public void removeChocolate(Long chocoId)  {
        Optional<Chocolate> choco = chocolateRepo.findById(chocoId);
        if(choco.isPresent()) {
            chocolateRepo.delete(choco.get());
        }
    }

    public Chocolate getChocolateById(Long id){
        Optional<Chocolate> choco = chocolateRepo.findById(id);
        return choco.orElse(null);
    }

    @Transactional
    public void updateChocolate(@Valid Chocolate choco) {
        Optional<Chocolate> chocolateBeforeUpdate = chocolateRepo.findById(choco.getId());
        if(chocolateBeforeUpdate.isPresent()) {
            chocolateRepo.updateChocolate(choco.getId(),choco.getName(),choco.getPrice(),choco.getStock(),choco.getType());
        }
    }
}
