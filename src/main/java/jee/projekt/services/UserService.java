/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jee.projekt.services;

/**
 *
 * @author matir
 */


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;
import jee.projekt.entities.User;
import jee.projekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUserList(){
        return (List<User>) userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void removeUser(Long studentId)  {
        Optional<User> user = userRepository.findById(studentId);
        if(user.isPresent()) {
            userRepository.delete(user.get());
        }
    }

    public User getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Transactional
    public void updateUser(User user) {
        Optional<User> userBeforeUpdate = userRepository.findById(user.getId());
        if(userBeforeUpdate.isPresent()) {
            userRepository.updateUser(user.getId(), user.getLogin(), user.getName(), user.getPassword());
        }
    }
}

