package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class RecieptDTO {
    private String consignmentId;
    private String warehouseId;
    private String toWarehouseId;
    private  String status;
    private String type;

}
