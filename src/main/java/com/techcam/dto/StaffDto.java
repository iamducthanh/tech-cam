package com.techcam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/10/2022 12:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDto {
 private String id;
 private String fullname;
 private String username;
 private String image;
}
