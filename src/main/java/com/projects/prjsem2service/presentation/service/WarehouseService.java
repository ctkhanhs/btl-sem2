package com.projects.prjsem2service.presentation.service;

import com.projects.prjsem2service.presentation.dto.CategoryDTO;
import com.projects.prjsem2service.presentation.dto.ConsignmentDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.WarehouseDTO;

import java.util.List;

public interface WarehouseService {
    List<WarehouseDTO> getAllWarehouse();
    List<WarehouseDTO> getAllWarehouseByType(String type);
    List<ConsignmentDTO> getAllConsignmentInWarehouse(String id);
    WarehouseDTO getWarehouseById(String id);
    List<WarehouseDTO> getAllWarehouseDeleted();
    ErrorCodeResponse saveWarehouse(WarehouseDTO request);

    ErrorCodeResponse deteleWarehouse(String id);
    ErrorCodeResponse restoreWarehouse(String id);
}
