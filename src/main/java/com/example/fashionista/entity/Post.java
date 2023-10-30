package com.example.fashionista.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tittle", nullable = false)
    private String tittle;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;



    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

//    @OneToMany (mappedBy = "postEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<CommentEntity> comments;
    @OneToMany(mappedBy = "post",fetch =FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment>comments= new HashSet<>();

    @OneToMany (mappedBy = "post",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like>likes;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

}
