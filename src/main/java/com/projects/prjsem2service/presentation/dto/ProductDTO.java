package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private String categoryId;
    private String categoryName;
    private String brand;
    private Double price;
    private String madeIn;
    private String image;
    private String code;
    private String status;
}
