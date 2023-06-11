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
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usersId" , nullable = false)
    private Long huespedId;

    @Column(name = "checkInDate", nullable = false)
    private String fechaIngreso;

    @Column(name = "checkOutDate", nullable = false)
    private String fechaSalida;

    @Column(name = "price", nullable = false)
    private String precio;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "time", nullable = false)
    private String tiempo;

}
