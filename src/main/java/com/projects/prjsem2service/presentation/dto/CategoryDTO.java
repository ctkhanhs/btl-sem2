package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class CategoryDTO {
    private String id;
    private String name;
    private String code;
    private String status;
}
