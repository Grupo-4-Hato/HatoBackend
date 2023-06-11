package com.upc.Hato.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="serving")
public class Serving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="serving_type", nullable = false, length = 50)
    private String servingType;
    @Column(name="order_date", nullable = false)
    private LocalDateTime orderDate;
    @Column(name="delivery_date", nullable = false)
    private LocalDateTime deliveryDate;
    /*Relaciones con otras entidades (Obtener informacion de usuario y habitacion)*/
}
