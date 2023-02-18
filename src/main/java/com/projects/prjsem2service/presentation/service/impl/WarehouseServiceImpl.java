package com.projects.prjsem2service.presentation.service.impl;

import com.projects.prjsem2service.persistence.entity.ConsignmentEntity;
import com.projects.prjsem2service.persistence.entity.PartnerEntity;
import com.projects.prjsem2service.persistence.entity.WarehouseEntity;
import com.projects.prjsem2service.persistence.repository.PartnerRepository;
import com.projects.prjsem2service.persistence.repository.WarehouseRepository;
import com.projects.prjsem2service.presentation.dto.ConsignmentDTO;
import com.projects.prjsem2service.presentation.dto.ErrorCodeResponse;
import com.projects.prjsem2service.presentation.dto.WarehouseDTO;
import com.projects.prjsem2service.presentation.service.WarehouseService;
import com.projects.prjsem2service.presentation.util.Constants;
import com.projects.prjsem2service.presentation.util.RecieptType;
import com.projects.prjsem2service.presentation.util.WarehouseType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private PartnerRepository partnerRepository;
    @Override
    public List<WarehouseDTO> getAllWarehouse() {
        try {
            List<WarehouseEntity> entities = warehouseRepository.findAllByStatus(Constants.STATUS_ACTIVE);
            List<WarehouseDTO> dtoList = new ArrayList<>();
            for(WarehouseEntity entity : entities){
                WarehouseDTO dto = new WarehouseDTO();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setCode(entity.getCode().toUpperCase(Locale.ROOT));
                dto.setCapacity(entity.getCapacity());
                dto.setAddress(entity.getAddress());
                dto.setPartnerName(entity.getPartner().getName());
                dtoList.add(dto);
            }
            return dtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WarehouseDTO> getAllWarehouseByType(String type) {
        try {
            String warehouseType = "";
            if(Constants.STATUS_ACTIVE.equalsIgnoreCase(type)){
                warehouseType = WarehouseType.NB.name();
            }
            if(Constants.STATUS_SALED.equalsIgnoreCase(type)){
                warehouseType = WarehouseType.KH.name();
            }
            List<WarehouseEntity> entities = warehouseRepository.findAllByStatusAndType(Constants.STATUS_ACTIVE,warehouseType);
            List<WarehouseDTO> dtoList = new ArrayList<>();
            for(WarehouseEntity entity : entities){
                WarehouseDTO dto = new WarehouseDTO();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setCode(entity.getCode().toUpperCase(Locale.ROOT));
                dto.setCapacity(entity.getCapacity());
                dto.setAddress(entity.getAddress());
                dto.setPartnerName(entity.getPartner().getName());
                dtoList.add(dto);
            }
            return dtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ConsignmentDTO> getAllConsignmentInWarehouse(String id) {
        try{
            WarehouseEntity warehouse = warehouseRepository.getById(id);
            List<ConsignmentEntity> consignmentEntities = warehouse.getConsignmentEntities();
            List<ConsignmentDTO> consignmentDTOList = new ArrayList<>();
            for(ConsignmentEntity entity : consignmentEntities){
                ConsignmentDTO dto = new ConsignmentDTO()
                        .withId(entity.getId())
                        .withCode(entity.getCode())
                        .withQuantity(entity.getQuantity())
                        .withStatus(entity.getStatus())
                        .withWarehouseId(entity.getWarehouse().getId())
                        .withImportAmount(entity.getImportTotal())
                        .withExportAmount(entity.getExportTotal());
                consignmentDTOList.add(dto);
            }
            return consignmentDTOList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WarehouseDTO getWarehouseById(String id) {
        WarehouseEntity entity = warehouseRepository.getById(id);
        WarehouseDTO dto = new WarehouseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setCapacity(entity.getCapacity());
        dto.setAddress(entity.getAddress());
        dto.setPartnerId(entity.getPartner().getId());
        dto.setPartnerName(entity.getPartner().getName());
        return dto;
    }

    @Override
    public List<WarehouseDTO> getAllWarehouseDeleted() {
        try {
            List<WarehouseEntity> entities = warehouseRepository.findAllByStatus(Constants.STATUS_UNACTIVE);
            List<WarehouseDTO> dtoList = new ArrayList<>();
            for(WarehouseEntity entity : entities){
                WarehouseDTO dto = new WarehouseDTO();
                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setCode(entity.getCode());
                dto.setCapacity(entity.getCapacity());
                dto.setAddress(entity.getAddress());
                dto.setPartnerName(entity.getPartner().getName());
                dtoList.add(dto);
            }
            return dtoList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ErrorCodeResponse saveWarehouse(WarehouseDTO request) {
        try {
            boolean checkNameExits = warehouseRepository.existsByName(request.getName());
            boolean checkCodeExits = warehouseRepository.existsByCode(request.getCode());

            WarehouseEntity entity = new WarehouseEntity();
            if(!StringUtils.isEmpty(request.getId())){
                entity.setId(request.getId());
            }else {
                if(checkNameExits){
                    return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,Constants.NAME_DUPLICATE_DESC);
                }
                if(checkCodeExits){
                    return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,Constants.CODE_DUPLICATE_DESC);
                }
                entity.setId(UUID.randomUUID().toString());
            }

            PartnerEntity partner = partnerRepository.getById(request.getPartnerId());
            entity.setId(UUID.randomUUID().toString());
            entity.setName(request.getName());
            entity.setCode(request.getCode());
            entity.setAddress(request.getAddress());
            entity.setCapacity(request.getCapacity());
            entity.setStatus(Constants.STATUS_ACTIVE);
            if(WarehouseType.NB.name().equalsIgnoreCase(partner.getCode())){
                entity.setType(WarehouseType.NB.name());
            }
            else {
                entity.setType(WarehouseType.KH.name());
            }
            entity.setPartner(partner);
            entity.setCreatedAt(new Date(System.currentTimeMillis()));
            warehouseRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public ErrorCodeResponse deteleWarehouse(String id) {
        try {
            WarehouseEntity entity= warehouseRepository.getById(id);
            entity.setStatus(Constants.STATUS_UNACTIVE);
            warehouseRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }

    @Override
    public ErrorCodeResponse restoreWarehouse(String id) {
        try {
            WarehouseEntity entity= warehouseRepository.getById(id);
            entity.setStatus(Constants.STATUS_ACTIVE);
            warehouseRepository.save(entity);
            return new ErrorCodeResponse(Constants.ERROR_CODE_SUCCESS,null);
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorCodeResponse(Constants.ERROR_CODE_FAILED,null);
        }
    }
}
