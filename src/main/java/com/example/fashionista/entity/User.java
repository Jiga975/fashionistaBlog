package com.example.fashionista.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String names;
    private String mail;
    private String password;
    @Transient
    private String ConfirmPassword;
    private String role;
    private String address;

    @OneToMany (mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post>posts;

    @OneToMany (mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment>commentEntities;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like>likes;



}
