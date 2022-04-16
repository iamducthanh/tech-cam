package com.techcam.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 1/24/2022 10:46 PM
 */

@Service
@RequiredArgsConstructor
public class SessionUtil {
 @Autowired
 private HttpServletRequest req;

 public void addObject(String key, Object value){
  req.getSession().setAttribute(key, value);
 }

 public Object getObject(String key){
  return req.getSession().getAttribute(key);
 }

 public void removeObject(String key){
  req.getSession().removeAttribute(key);
 }

}
