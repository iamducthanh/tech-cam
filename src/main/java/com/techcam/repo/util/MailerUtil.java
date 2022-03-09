package com.techcam.repo.util;

import com.techcam.dto.request.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 2/12/2022 5:31 PM
 */

@Component
public class MailerUtil {
 @Autowired
 private JavaMailSender sender;

 public void send(MailDto mail) throws MessagingException {
  MimeMessage message = sender.createMimeMessage();
  MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
  helper.setFrom(mail.getFrom());
  helper.setTo(mail.getTo());
  helper.setSubject(mail.getSubject());
  helper.setText(mail.getBody(), true);
  helper.setReplyTo(mail.getFrom());
  sender.send(message);
 }
}
