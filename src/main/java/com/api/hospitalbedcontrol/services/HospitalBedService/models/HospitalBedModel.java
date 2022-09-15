package com.api.hospitalbedcontrol.services.HospitalBedService.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_HOSPITAL_BED_CONTROL")
public class HospitalBedModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 100)
    private String fullNamePatient;
    @Column(nullable = false, unique = true, length = 10)
    private String hospitalBedNumber;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false, length = 30)
    private String bedroom;

    public HospitalBedModel() {
    }
    public HospitalBedModel(String fullNamePatient,
                            String hospitalBedNumber,
                            String bedroom) {
        this.fullNamePatient = fullNamePatient;
        this.hospitalBedNumber = hospitalBedNumber;
        this.bedroom = bedroom;
    }

    public String getFullNamePatient() {
        return fullNamePatient;
    }

    public void setFullNamePatient(String fullNamePatient) {
        this.fullNamePatient = fullNamePatient;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHospitalBedNumber() {
        return hospitalBedNumber;
    }

    public void setHospitalBedNumber(String hospitalBedNumber) {
        this.hospitalBedNumber = hospitalBedNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }
}
