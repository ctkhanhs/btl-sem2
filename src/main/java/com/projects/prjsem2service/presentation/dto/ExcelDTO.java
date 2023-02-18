package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelDTO {
    private String productCode;
    private String productName;
    private String categoryCode;
    private String categoryName;
    private String brand;
    private String madeIn;
    private Integer numberProductsInBox;
    private Integer numberOfBoxes;
    private Double salePrice;
    private Double importPrice;
    private String nsx;
    private String hsd;
}
