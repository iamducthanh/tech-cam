package com.techcam.service.impl;

import com.techcam.entity.StaffEntity;
import com.techcam.repo.IStaffRepo;
import com.techcam.service.IStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Service
@RequiredArgsConstructor
public class StaffService implements IStaffService {
    private final IStaffRepo repo;

    @Override
    public StaffEntity getByEmail(String email) {
        List<StaffEntity> list = repo.findByEmail(email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void saveStaff(StaffEntity staffEntity) {
        repo.save(staffEntity);
    }


    public static void main(String[] args)  {
//        String secretKey = "TVDqqqqqqqq";
//        String originalString = "teamvietdev.com";
//
//        String encryptedString = encrypt(originalString, secretKey);
//        System.out.println("Encrypt: " + encryptedString);
//        String decryptedString = decrypt(encryptedString, secretKey);
//        System.out.println("Decrypt: " + decryptedString);
    }
}
