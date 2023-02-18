package com.projects.prjsem2service.presentation.controller;

import com.projects.prjsem2service.presentation.dto.CategoryDTO;
import com.projects.prjsem2service.presentation.dto.ConsignmentDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.WarehouseDTO;
import com.projects.prjsem2service.presentation.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin(origins = "http://localhost:4200")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping(value = "/get-all-warehouse")
    public List<WarehouseDTO> getListWarehouse(){
        return warehouseService.getAllWarehouse();
    }

    @PostMapping(value = "/save-warehouse")
    public ErrorCodeResponse saveWarehouse(@RequestBody WarehouseDTO warehouseDTO){
        return warehouseService.saveWarehouse(warehouseDTO);
    }
    @GetMapping(value ="/get-warehouse")
    public WarehouseDTO getWarehouseById(@RequestParam(value = "id") String id){
        return warehouseService.getWarehouseById(id);
    }

    @GetMapping(value ="/get-all-warehouse-by-type")
    public List<WarehouseDTO> getAllWarehouseByType(@RequestParam(value = "type") String type){
        return warehouseService.getAllWarehouseByType(type);
    }

    @GetMapping(value ="/get-all-consignment-in-warehouse")
    public List<ConsignmentDTO> getAllConsignmentInWarehouse(@RequestParam(value = "id") String id){
        return warehouseService.getAllConsignmentInWarehouse(id);
    }


}
