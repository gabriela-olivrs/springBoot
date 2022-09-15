package com.api.hospitalbedcontrol.services.HospitalBedService.services;

import com.api.hospitalbedcontrol.services.HospitalBedService.models.HospitalBedModel;
import com.api.hospitalbedcontrol.services.HospitalBedService.repositories.HospitalBedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class HospitalBedService {

    final HospitalBedRepository hospitalBedRepository;

    public HospitalBedService(HospitalBedRepository hospitalBedRepository) {
        this.hospitalBedRepository = hospitalBedRepository;
    }

    @Transactional
    public HospitalBedModel save(HospitalBedModel hospitalBedModel) {
        return hospitalBedRepository.save(hospitalBedModel);
    }
    @Transactional
    public void delete(HospitalBedModel hospitalBedModel) {
        hospitalBedRepository.delete(hospitalBedModel);
    }


    public boolean existsByHospitalBedNumber(String hospitalBedNumber) {
        return hospitalBedRepository.existsByHospitalBedNumber(hospitalBedNumber);
    }

    public boolean existsByBedroom(String bedroom) {
        return hospitalBedRepository.existsByBedroom(bedroom);
    }

    public Page<HospitalBedModel> findAll(Pageable pageable) {
        return hospitalBedRepository.findAll(pageable);
    }

    public Optional<HospitalBedModel> findById(UUID id) {
        return hospitalBedRepository.findById(id);
    }



}
