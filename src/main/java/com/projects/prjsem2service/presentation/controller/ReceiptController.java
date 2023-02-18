package com.projects.prjsem2service.presentation.controller;

import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.ProductDTO;
import com.projects.prjsem2service.presentation.dto.ReceiptWarehouseDTO;
import com.projects.prjsem2service.presentation.dto.RecieptDTO;
import com.projects.prjsem2service.presentation.service.RecieptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipt")
@CrossOrigin(origins = "http://localhost:4200")
public class ReceiptController {
    @Autowired
    private RecieptService recieptService;
    @PostMapping(value = "/create-receipt")
    public ErrorCodeResponse createReceipt(@RequestBody RecieptDTO recieptDTO){
        return recieptService.createReciept(recieptDTO);
    }

    @GetMapping(value = "/get-receipt")
    public List<ReceiptWarehouseDTO> createReceipt(@RequestParam(value = "warehouseName") String name){
        return recieptService.getReceiptByWarehouseCode(name);
    }
}
