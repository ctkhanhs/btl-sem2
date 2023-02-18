package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;


@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class ConsignmentDTO {
    private String id;
    private String code;
    private Integer quantity;
    private Double amount;
    private String status;
    private String warehouseId;
    private String toWarehouseId;
    private String warehouseName;
    private String warehouseCode;
    private Double importAmount;
    private Double exportAmount;
}
