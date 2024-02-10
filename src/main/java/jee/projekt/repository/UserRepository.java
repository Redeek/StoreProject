/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jee.projekt.repository;


import jee.projekt.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("UPDATE User SET login = :login, name = :name, password = :password where id = :id")
    @Modifying
    void updateUser(@Param("id") Long id, @Param("login") String login, @Param("name") String name, @Param("password") String password);

    User findByLogin(String login);
}
