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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Wpisz co najmniej 2 znaki")
    @NotEmpty(message = "Login nie może być pusty! ")
    private String login;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Wpisz co najmniej 2 znaki")
    @NotEmpty(message = "Twoje imie nie może być puste! ")
    private String name;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Wpisz co najmniej 2 znaki")
    @NotEmpty(message = "Hasło nie może być puste! ")
    @Size(min = 8, message = "Hasło musi mieć min. 8 znaków")
    private String password;

    
}

