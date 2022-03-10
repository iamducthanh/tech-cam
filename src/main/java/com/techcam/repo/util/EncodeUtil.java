package com.techcam.repo.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 2/12/2022 5:20 PM
 */
@Component
public class EncodeUtil {
 String myKey = "TECH12345@#$";

 public String encrypt(String strToEncrypt) {
  try {
   MessageDigest sha = MessageDigest.getInstance("SHA-1");
   byte[] key = myKey.getBytes("UTF-8");
   key = sha.digest(key);
   key = Arrays.copyOf(key, 16);
   SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
   Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
   cipher.init(Cipher.ENCRYPT_MODE, secretKey);
   return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
  } catch (Exception e) {
   System.out.println(e.toString());
  }
  return null;
 }

 public String decrypt(String strToDecrypt) {
  try {
   MessageDigest sha = MessageDigest.getInstance("SHA-1");
   byte[] key = myKey.getBytes("UTF-8");
   key = sha.digest(key);
   key = Arrays.copyOf(key, 16);
   SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
   Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
   cipher.init(Cipher.DECRYPT_MODE, secretKey);
   return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
  } catch (Exception e) {
   e.printStackTrace();
   return null;
  }
 }
}
