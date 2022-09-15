package com.api.hospitalbedcontrol.services.HospitalBedService.repositories;

import com.api.hospitalbedcontrol.services.HospitalBedService.models.HospitalBedModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalBedRepository extends JpaRepository<HospitalBedModel, UUID> {
    boolean existsByHospitalBedNumber(String hospitalBedNumber);
    boolean existsByBedroom(String bedroom);
}
