package com.projects.prjsem2service.presentation.service;

import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.ReceiptWarehouseDTO;
import com.projects.prjsem2service.presentation.dto.RecieptDTO;

import java.util.List;

public interface RecieptService {
    ErrorCodeResponse createReciept(RecieptDTO request);
    List<ReceiptWarehouseDTO> getReceiptByWarehouseCode(String name);
}
