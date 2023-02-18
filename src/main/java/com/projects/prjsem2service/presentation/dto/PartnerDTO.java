package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDTO {
    private String id;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String status;
}
