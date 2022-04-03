package com.techcam.api;


import com.techcam.dto.request.brand.BrandAddRequestDTO;
import com.techcam.dto.request.brand.BrandEditRequestDTO;
import com.techcam.dto.request.staff.StaffEditRequestDTO;
import com.techcam.dto.response.brand.BrandResponse;
import com.techcam.dto.response.staff.StaffResponseDTO;
import com.techcam.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
public class BrandApi {
    @Autowired
    private IBrandService iBrandService;


    @GetMapping("/{id}")
    public BrandResponse findById(@PathVariable("id") String id) {
        return iBrandService.findById(id);
    }

    @GetMapping("/find/email/{value}")
    public Integer findByEmail(@PathVariable("value") String value) {
        return iBrandService.findByEmailaAndDeleteFlagIsFalse(value);
    }

    @GetMapping("/find/phone/{value}")
    public Integer findByPhone(@PathVariable("value") String value) {
        return iBrandService.findByPhoneAndDeleteFlagIsFalse(value);
    }


    @PostMapping
    public ResponseEntity addBrand(@RequestBody BrandAddRequestDTO brandResponse){
        String status = iBrandService.addBrand(brandResponse);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(brandResponse);
        }
        return ResponseEntity.badRequest().body(brandResponse);
    }

    @PutMapping
    public ResponseEntity updateBrand(@RequestBody BrandEditRequestDTO dto) {
        iBrandService.editBrand(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity deleteStaff(@PathVariable("id") String id) {
        if (iBrandService.deleteBrand(id)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity deleteStaff(@PathVariable("id") String id, @PathVariable("status") String status) {
        if (iBrandService.changeStatusBrand(id, status)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }

}
