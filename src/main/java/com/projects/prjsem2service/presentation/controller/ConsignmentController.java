package com.projects.prjsem2service.presentation.controller;

import com.projects.prjsem2service.presentation.dto.*;
import com.projects.prjsem2service.presentation.service.ConsignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/consignment")
@CrossOrigin(origins = "http://localhost:4200")
public class ConsignmentController {
    @Autowired
    private ConsignmentService consignmentService;

    @PostMapping(value = "/save-consignment")
    public ErrorCodeResponse saveConsignment(@RequestParam("file") MultipartFile reapExcelDataFile) {
        return consignmentService.createConsignment(reapExcelDataFile);
    }
    @PostMapping(value = "/update-consignment")
    public ErrorCodeResponse updateConsignment(@RequestBody ConsignmentDTO consignmentDTO) {
        return consignmentService.updateConsignment(consignmentDTO);
    }
    @GetMapping(value = "/get-all-consignment")
    public List<ConsignmentDTO> getListConsignment(){
        return consignmentService.consignmentList();
    }

    @GetMapping(value = "/get-all-new-consignment")
    public List<ConsignmentDTO> getListNewConsignment(){
        return consignmentService.newConsignmentList();
    }

    @GetMapping(value = "/get-consignment")
    public ConsignmentDTO getConsignmentById(@RequestParam(value = "id") String id) {
        return consignmentService.getOne(id);
    }

    @GetMapping(value = "/get-consignment-details")
    public List<ConsignmentDetailsDTO> getConsignmentDetails(@RequestParam(value = "id") String id) {
        return consignmentService.getAllConsignmentDetails(id);
    }

    @GetMapping(value = "/get-report")
    public ReportDTO getConsignmentDetails() {
        return consignmentService.getReport();
    }
}
