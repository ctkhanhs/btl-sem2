package com.projects.prjsem2service.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO {
    private Integer saled;
    private Integer active;
    private Double totalImportAmount;
    private Double totalExportAmount;
    private Double revenue;
}
