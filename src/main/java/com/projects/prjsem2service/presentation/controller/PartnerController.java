package com.projects.prjsem2service.presentation.controller;

import com.projects.prjsem2service.presentation.dto.CategoryDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.PartnerDTO;
import com.projects.prjsem2service.presentation.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partner")
@CrossOrigin(origins = "http://localhost:4200")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @GetMapping(value = "/get-all-partner")
    public List<PartnerDTO> getListCategory(){
        return partnerService.getAllPartner();
    }

    @PostMapping(value = "/save-partner")
    public ErrorCodeResponse saveCategory(@RequestBody PartnerDTO partnerDTO){
        return partnerService.savePartner(partnerDTO);
    }
    @GetMapping(value ="/get-partner")
    public PartnerDTO getPartnerById(@RequestParam(value = "id") String id){
        return partnerService.getPartnerById(id);
    }
}
