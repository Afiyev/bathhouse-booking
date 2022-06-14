package com.bathhouse.booking.service;

import com.bathhouse.booking.repository.BathhouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeControllerServiceImpl implements HomeControllerService{

    @Autowired
    private BathhouseRepository bathhouseRepository;

    @Override
    public int countBathhouseByCity(String city) {
        return bathhouseRepository.countBathhouseByCity(city);
    }
}
