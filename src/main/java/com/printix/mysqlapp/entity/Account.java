package com.printix.mysqlapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;

    @Column(name = "account_id", columnDefinition = "char(36)")
    @Convert(converter = UUIDStringConverter.class)
    private UUID accountId;

    @PrePersist
    private void initializeUUID() {
        if (accountId == null) {
            accountId = UUID.randomUUID();
        }
    }
}
