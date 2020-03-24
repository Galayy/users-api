package com.itechart.container.spring.users.entity;

import com.itechart.container.spring.users.entity.enums.UserEntityRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.UUID;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    protected UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login", nullable = false, updatable = false, unique = true)
    private String login;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Enumerated(STRING)
    @Type(type = "enumType")
    @Column(name = "role", nullable = false, updatable = false)
    private UserEntityRole role;

}
