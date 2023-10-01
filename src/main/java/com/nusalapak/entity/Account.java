package com.nusalapak.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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

}
