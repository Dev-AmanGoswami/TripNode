package com.example.tripnode.controller;

import com.example.tripnode.dto.DriverRequest;
import com.example.tripnode.dto.DriverResponse;
import com.example.tripnode.service.driver.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverResponse>> getAllDrivers(){
        return ResponseEntity
                .ok(driverService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable Long id){
        return driverService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<DriverResponse> getDriverByEmail(@PathVariable String email){
        return driverService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public ResponseEntity<List<DriverResponse>> getAllAvailableDrivers(){
        List<DriverResponse> availableDrivers = driverService.findAvailableDrivers();
        return ResponseEntity.ok(availableDrivers);
    }

    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@RequestBody DriverRequest request){
        try{
            DriverResponse driver = driverService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(driver);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponse> updateDriver(
            @PathVariable Long id,
            @RequestBody DriverRequest request
    ){
        try{
            DriverResponse driver = driverService.update(id, request);
            return ResponseEntity.ok(driver);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id){
        try{
            driverService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
