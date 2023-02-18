package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@NoArgsConstructor
@Data
@AllArgsConstructor
@With
public class ReceiptWarehouseDTO {
    private String code;
    private String consignmentCode;
    private String consignmentId;
    private String warehouseNameSent;
    private String warehouseAddressSent;
    private String warehouseNameReceive;
    private String warehouseAddressReceive;
    private String type;
}
