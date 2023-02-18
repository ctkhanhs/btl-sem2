package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    private String id;
    private String name;
    private Integer capacity;
    private String address;
    private String code;
    private String status;
    private String partnerId;
    private String partnerName;
}
