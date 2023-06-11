package com.upc.Hato.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="payment_type", nullable = false, length = 50)
    private String paymentType;
    @Column(name="card_number", nullable = false, length = 16)
    private String cardNumber;
    @Column(name="card_holder", nullable = false, length = 50)
    private String cardHolder;
    @Column(name="expiration_date", nullable = false, length = 5)
    private String expirationDate;
    @Column(name="cvv", nullable = false, length = 3)
    private String cvv;
}
