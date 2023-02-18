package com.projects.prjsem2service.presentation.service;

import com.projects.prjsem2service.presentation.dto.ConsignmentDetailsDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;

public interface ConsignmentDetailsService {
    ErrorCodeResponse saveConsignmentDetails(ConsignmentDetailsDTO request);
}
