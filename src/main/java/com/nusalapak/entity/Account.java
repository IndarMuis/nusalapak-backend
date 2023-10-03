package com.nusalapak.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts", indexes = @Index(name = "email", columnList = "email"))
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Authority> authorities;

}
