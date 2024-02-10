/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jee.projekt.entities;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chocolate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Wpisz co najmniej 2 znaki")
    @NotBlank(message = "Podaj nazwę!")
    private String name;
    
    
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Wpisz co najmniej 2 znaki")
    @NotBlank(message = "Podaj rodzaj czekolady!")
    private String type;
    
    
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Wpisz co najmniej 2 znaki")
    @Min(value = 1, message = "Cena musi być większa od 0")
    private float price;
    
    
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Wpisz co najmniej 2 znaki")
    @Min(value = 1, message = "Liczba dostępnych sztuk musi wynosić co najmniej 1!")
    private int stock;
    
}
