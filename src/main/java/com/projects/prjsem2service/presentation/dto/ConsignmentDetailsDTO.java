package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;


@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class ConsignmentDetailsDTO {
    private String id;
    private String productId;
    private String consignmentId;
    private String productName;
    private String categoryName;
    private String productCode;
    private Integer numberOfBoxes;
    private Integer numberProductsInBox;
    private Double importPrice;
    private String nsx;
    private String hsd;
    private Double exportPrice;
}
