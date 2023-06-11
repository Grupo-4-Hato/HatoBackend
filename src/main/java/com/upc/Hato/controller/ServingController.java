package com.upc.Hato.controller;

import com.upc.Hato.exception.ValidationException;
import com.upc.Hato.model.Serving;
import com.upc.Hato.repository.ServingRepository;
import com.upc.Hato.service.ServingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/hato")
public class ServingController {
    @Autowired
    private ServingService servingService;
    private final ServingRepository servingRepository;
    public ServingController(ServingRepository servingRepository) {
        this.servingRepository = servingRepository;
    }

    //EndPoint: http://localhost:8080/api/hato/servings
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/servings")
    public ResponseEntity<List<Serving>> getAllServings() {
        return new ResponseEntity<List<Serving>>(servingRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/hato/servings/filterByServingType
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/servings/filterByServingType")
    public ResponseEntity<List<Serving>> getServingsByServingType(@RequestParam(name = "servingType") String servingType) {
        return new ResponseEntity<List<Serving>>(servingRepository.findByServingType(servingType), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/hato/servings
    //Method: POST
    @Transactional
    @PostMapping("/servings")
    public ResponseEntity<Serving> createServing(@RequestBody Serving serving) {
        validateServing(serving);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        LocalTime orderTime = LocalTime.parse(now.format(inputFormatter), inputFormatter);
        LocalTime deliveryTime = orderTime.plusMinutes(5);
        serving.setOrderDate(now.with(orderTime));
        switch (serving.getServingType()) {
            case "Limpieza" -> deliveryTime = orderTime.plusMinutes(10);
            case "SOS" -> deliveryTime = orderTime.plusMinutes(1);
            case "Pedido" -> deliveryTime = orderTime.plusMinutes(5);
        }
        serving.setDeliveryDate(now.with(deliveryTime));
        return new ResponseEntity<>(servingService.createServing(serving), HttpStatus.CREATED);
    }
    public void validateServing(Serving serving) {
        if (serving.getServingType() == null || serving.getServingType().trim().isEmpty()) {
            throw new ValidationException("The serving's type is required");
        }
        if (serving.getServingType().length() > 20) {
            throw new ValidationException("The serving's type should not exceed 20 characters");
        }
        if (!serving.getServingType().equals("Limpieza") &&
                !serving.getServingType().equals("Pedido") &&
                !serving.getServingType().equals("SOS")) {
            throw new ValidationException("Invalid serving type. Only 'Limpieza', 'Pedido', and 'SOS' are allowed");
        }
    }
}
