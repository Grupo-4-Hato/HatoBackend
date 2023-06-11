package com.upc.Hato.service.Impl;

import com.upc.Hato.model.Serving;
import com.upc.Hato.repository.ServingRepository;
import com.upc.Hato.service.ServingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServingServiceImpl implements ServingService {
    @Autowired
    private ServingRepository servingRepository;
    @Override
    public Serving createServing(Serving serving) { return servingRepository.save(serving);}
}
