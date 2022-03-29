package com.techcam.api;

import com.techcam.dto.request.SupplierDTO;
import com.techcam.dto.response.SupplierResponseDTO;
import com.techcam.service.ISupplierService;
import com.techcam.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierAPI {
    @Autowired
    ISupplierService supplierService;

    @GetMapping("/get-all")
    public ResponseEntity<List<SupplierResponseDTO>> getAll(){
        List<SupplierResponseDTO> supplierResponseDTOS = supplierService.findAllByDeleteFlagFalse();
        return ResponseEntity.ok(supplierResponseDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity<SupplierResponseDTO> create(@Valid @RequestBody SupplierDTO supplierDTO){
        SupplierResponseDTO supplierResponseDTO = supplierService.create(supplierDTO);
        return ResponseEntity.ok(supplierResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SupplierResponseDTO> create(@Valid @RequestBody SupplierDTO supplierDTO, @PathVariable("id") String id){
        SupplierResponseDTO supplierResponseDTO = supplierService.update(supplierDTO, id);
        return ResponseEntity.ok(supplierResponseDTO);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<SupplierResponseDTO> findById(@PathVariable("id") String id){
        SupplierResponseDTO supplierResponseDTO = supplierService.findById(id);
        return ResponseEntity.ok(supplierResponseDTO);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        supplierService.delete(id);
        return ResponseEntity.ok("ok");
    }
}
