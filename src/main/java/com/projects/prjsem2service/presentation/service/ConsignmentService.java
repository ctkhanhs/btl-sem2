package com.projects.prjsem2service.presentation.service;

import com.projects.prjsem2service.presentation.dto.ConsignmentDTO;
import com.projects.prjsem2service.presentation.dto.ConsignmentDetailsDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.ReportDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConsignmentService {
    ErrorCodeResponse createConsignment(MultipartFile excelFile);
    List<ConsignmentDTO> newConsignmentList();
    List<ConsignmentDTO> consignmentList();
    ErrorCodeResponse updateConsignment(ConsignmentDTO dto);
    ConsignmentDTO getOne(String id);
    List<ConsignmentDetailsDTO> getAllConsignmentDetails(String id);

    ReportDTO getReport();
}
