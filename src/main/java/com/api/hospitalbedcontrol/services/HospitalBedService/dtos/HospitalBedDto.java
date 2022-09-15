package com.api.hospitalbedcontrol.services.HospitalBedService.dtos;

import javax.validation.constraints.NotBlank;

public class HospitalBedDto {

    @NotBlank
    private String fullNamePatient;
    @NotBlank
    private String hospitalBedNumber;
    @NotBlank
    private String bedroom;

    public String getFullNamePatient() {
        return fullNamePatient;
    }

    public void setFullNamePatient(String fullNamePatient) {
        this.fullNamePatient = fullNamePatient;
    }

    public String getHospitalBedNumber() {

        return hospitalBedNumber;
    }

    public void setHospitalBedNumber(String hospitalBedNumber) {
        this.hospitalBedNumber = hospitalBedNumber;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }
}
