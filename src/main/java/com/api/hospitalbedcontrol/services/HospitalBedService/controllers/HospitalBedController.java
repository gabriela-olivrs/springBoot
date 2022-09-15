package com.api.hospitalbedcontrol.services.HospitalBedService.controllers;

import com.api.hospitalbedcontrol.services.HospitalBedService.dtos.HospitalBedDto;
import com.api.hospitalbedcontrol.services.HospitalBedService.models.HospitalBedModel;
import com.api.hospitalbedcontrol.services.HospitalBedService.services.HospitalBedService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/hospital-bed-control")
public class HospitalBedController {
    final HospitalBedService hospitalBedService;

    public HospitalBedController(HospitalBedService hospitalBedService) {
        this.hospitalBedService = hospitalBedService;
    }

    @PostMapping
    public ResponseEntity<Object> saveHospitalBed(@RequestBody @Valid HospitalBedDto hospitalBedDto){

        if(hospitalBedService.existsByHospitalBedNumber(hospitalBedDto.getHospitalBedNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Hospital Bed Number in use!");
        }
        if(hospitalBedService.existsByBedroom(hospitalBedDto.getBedroom())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Bedroom in use!");
        }

        var hospitalBedModel = new HospitalBedModel();
        BeanUtils.copyProperties(hospitalBedDto, hospitalBedModel);
        hospitalBedModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalBedService.save(hospitalBedModel));

    }

    @GetMapping
    public ResponseEntity<Page<HospitalBedModel>> getAllHospitalBed(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(hospitalBedService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneHospitalBed(@PathVariable(value = "id") UUID id){
        Optional<HospitalBedModel> hospitalBedModelOptional = hospitalBedService.findById(id);
        if (!hospitalBedModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital-Bed not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(hospitalBedModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHospitalBed(@PathVariable(value = "id") UUID id){
        Optional<HospitalBedModel> hospitalBeModelOptional = hospitalBedService.findById(id);
        if (!hospitalBeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital-bed not found.");
        }
        hospitalBedService.delete(hospitalBeModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Hospital Bed deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHospitalBed(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid HospitalBedDto hospitalBedDto){
        Optional<HospitalBedModel> hospitalBeModelOptional = hospitalBedService.findById(id);
        if (!hospitalBeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital-Bed not found.");
        }
        var hospitalBedModel = new HospitalBedModel();
        BeanUtils.copyProperties(hospitalBedDto, hospitalBedModel);
        hospitalBedModel.setId(hospitalBeModelOptional.get().getId());
        hospitalBedModel.setRegistrationDate(hospitalBeModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(hospitalBedService.save(hospitalBedModel));
    }



}
