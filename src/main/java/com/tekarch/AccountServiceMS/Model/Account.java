package com.tekarch.AccountServiceMS.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "accounts")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long accountId;

        @Column(nullable = false)
        private Integer userId;

        @Column(unique = true, nullable = false, length = 20)
        private String accountNumber;

        @Column(nullable = false, length = 20)
        private String accountType;

        @Column(precision = 15, scale = 2, nullable = false)
        private BigDecimal balance = BigDecimal.ZERO;

        @Column(length = 10, nullable = false)
        private String currency = "USD";

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt = LocalDateTime.now();
}
