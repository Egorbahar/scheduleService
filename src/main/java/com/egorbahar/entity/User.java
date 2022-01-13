package com.egorbahar.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, message = "Не меньше 5 знаков")
    private String username;
    @Size(min=2, message = "Не меньше 5 знаков")
    private String password;
    @NotNull(message = "not null")
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Size(min=3, max = 50)
    private String surname;
    @Size(min=7, message = "Не меньше 5 знаков")
    private String email;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
