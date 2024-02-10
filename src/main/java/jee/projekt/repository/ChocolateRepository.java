/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jee.projekt.repository;

import jee.projekt.entities.Chocolate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChocolateRepository extends CrudRepository<Chocolate,Long> {

    @Query ("update Chocolate set name = :name, price = :price, stock = :stock, type = :type where id = :chocolateId")
    @Modifying
    void updateChocolate(
            @Param("chocolateId") Long chocolateId,
            @Param("name") String name,
            @Param("price") Float price,
            @Param("stock") Integer stock,
            @Param("type") String type
            );

}

