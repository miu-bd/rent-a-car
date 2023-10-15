package com.miu.swe.service;

import com.miu.swe.model.Station;
import com.miu.swe.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    private StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> findAll() {
        return stationRepository.findAll();
    }
    public boolean existsById(Integer id) {
        return stationRepository.existsById(id);
    }


}
