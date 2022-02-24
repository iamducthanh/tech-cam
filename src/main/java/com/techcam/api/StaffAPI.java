package com.techcam.api;

import com.techcam.dto.request.StaffAddRequestDTO;
import com.techcam.dto.request.StaffEditRequestDTO;
import com.techcam.dto.response.StaffResponseDTO;
import com.techcam.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * <p>
 * Description : receive and send all requests or responses about staff
 */
@RestController
@RequestMapping("/api/staff")
public class StaffAPI {

    @Autowired
    private IStaffService staffService;

    @GetMapping("/{id}")
    public StaffResponseDTO findById(@PathVariable("id") String id) {
        return staffService.findById(id);
    }

    @PostMapping
    public ResponseEntity addStaff(@RequestBody StaffAddRequestDTO staff) {
        String status = staffService.addStaff(staff);
        if (status.equalsIgnoreCase("ok")) {
            return ResponseEntity.ok().body(staff);
        }
        return ResponseEntity.badRequest().body(staff);
    }

    @PutMapping
    public ResponseEntity updateStaff(@RequestBody StaffEditRequestDTO staff) {
        staffService.editStaff(staff);
        return ResponseEntity.ok().body(staff);
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity deleteStaff(@PathVariable("id") String id, @PathVariable("status") String status) {
        if (staffService.changeStatusStaff(id, status)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity deleteStaff(@PathVariable("id") String id) {
        if (staffService.deleteStaff(id)) {
            return ResponseEntity.ok("oke");
        }
        return ResponseEntity.badRequest().body("fail");
    }
}
